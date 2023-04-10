import './App.css';
import { Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@mui/material';
import { Component } from 'react';
import Loader from './components/Loader';
import { BarChart, CartesianGrid, Legend, ResponsiveContainer, XAxis, YAxis, Bar, Tooltip, Label } from 'recharts';

const transferFeeMap = new Map();
const transferToMap = new Map();
const transferFromMap = new Map();
const netSpendMap = new Map();

class App extends Component {

  state = {
    tData: {},
    loading: false,
    clubs: [],
    netSpendValues: [],
    chartData: []
  }

  componentDidMount(){
    this.fetchTransferData();
  }

  fetchTransferData = async () => {
    this.setState({loading: true})
    const res = await fetch('http://localhost:5000/teams')
    this.setState({ tData: await res.json(), loading: false })
    // console.log(this.state.tData)
    this.setMaps(this.state.tData)
  }

  setMaps(transferData){

    var inTotal = 0
    var outTotal = 0

    for (let i=0; i < transferData.length; i++){

      for (let j=0; j < transferData[i].ins.length; j++){
        transferFeeMap.set(transferData[i].ins[j].player, this.getFee(transferData[i].ins[j].fee))
        transferToMap.set(transferData[i].ins[j].player, transferData[i].team)
        transferFromMap.set(transferData[i].ins[j].player, transferData[i].ins[j].from)
        inTotal += this.getFee(transferData[i].ins[j].fee)
      }

      for (let k=0; k < transferData[i].outs.length; k++){
        outTotal += this.getFee(transferData[i].outs[k].fee)
      }

      netSpendMap.set(transferData[i].teamShort, outTotal - inTotal)

      inTotal = 0
      outTotal = 0

    }

  }

  getFee(fee){
    if (fee === "Loan" || fee === "Free"){
      return 0
    } else {
      return fee
    }
  }

  getFeeString(value){
    if (value !== "Loan" && value !== "Free"){
      value = "£"+(value/1000000)+"m"
    }
    return value
  }

  getClubInsOutsTotal(teamInsOuts){
    var total = 0

    teamInsOuts.map((t) => {total += this.getFee(t.fee)})

    return total
  }

  getFontColour(teamColor){
    var fontColor = ""
    if (teamColor === "white"){
      fontColor = "black"
    } else {
      fontColor = "white"
    }
    return fontColor
  }

  getTopTenTransfers(){
    const topTenMap = new Map([...transferFeeMap.entries()].sort((a, b) => b[1] - a[1]))
    return topTenMap
  }

  getChartData(){
    var cData = []

    for (let i=0; i < netSpendMap.size; i++){
      cData = [...cData, {club: Array.from(netSpendMap.keys())[i], netSpend: netSpendMap.get(Array.from(netSpendMap.keys())[i])}]
    }

    return cData
  }

  render(){
    return (
      <>
        <h1 style={{display: "flex", alignItems: "center", justifyContent: "center"}}>Premier League Transfers 23/24</h1>

        { !this.state.loading ?
        <>
          {Object.values(this.state.tData).map((t) => (
            <TableContainer style={{marginBottom: "5%", width: "70%", marginLeft: "15%", display: "flex", alignItems: "center", justifyContent: "center", border: "1px solid", borderColor: "black"}} component={Paper}>
              <Table size="small" style={{width: "100%"}}>
                <TableBody>

                  <TableRow style={{backgroundColor: t.teamBGC, height: "55px"}}>
                    <TableCell align="center" colSpan={3} style={{fontWeight: 600, color: this.getFontColour(t.teamBGC)}}>{t.team}</TableCell>
                  </TableRow>

                  <TableRow>
                    <TableCell align='left' colSpan={3} style={{backgroundColor: "green"}}>INS:-</TableCell>
                  </TableRow>

                  <TableRow style={{backgroundColor: "lightgray"}}>
                    <TableCell>Player</TableCell>
                    <TableCell>From</TableCell>
                    <TableCell>Fee</TableCell>
                  </TableRow>

                  {t.ins.map((row) => (
                    <TableRow>
                      <TableCell>{row.player}</TableCell>
                      <TableCell>{row.from}</TableCell>
                      <TableCell>{this.getFeeString(row.fee)}</TableCell>
                      {/* {this.addToTransferMaps(row.player, this.getFee(row.fee), t.team, row.from)} */}
                    </TableRow>
                  ))}

                  <TableRow style={{backgroundColor: "lightgray"}}>
                    <TableCell align="right" colSpan={2}>TOTAL</TableCell>
                    <TableCell>{this.getFeeString(this.getClubInsOutsTotal(t.ins))}</TableCell>
                  </TableRow>

                  <TableRow>
                    <TableCell align='left' colSpan={3} style={{backgroundColor: "red"}}>OUTS:-</TableCell>
                  </TableRow>

                  <TableRow style={{backgroundColor: "lightgray"}}>
                    <TableCell colSpan={2}>Player</TableCell>
                    <TableCell>Fee</TableCell>
                  </TableRow>

                  {t.outs.map((row) => (
                    <TableRow>
                      <TableCell colSpan={2}>{row.player}</TableCell>
                      <TableCell>{this.getFeeString(row.fee)}</TableCell>
                    </TableRow>
                  ))}

                  <TableRow style={{backgroundColor: "lightgray"}}>
                    <TableCell align="right" colSpan={2}>TOTAL</TableCell>
                    <TableCell>{this.getFeeString(this.getClubInsOutsTotal(t.outs))}</TableCell>
                  </TableRow>

                  <TableRow style={{backgroundColor: "gray"}}>
                    <TableCell align="right" colSpan={2}>NET SPEND</TableCell>
                    <TableCell>{this.getFeeString(this.getClubInsOutsTotal(t.outs) - this.getClubInsOutsTotal(t.ins))}</TableCell>
                    {/* {this.addToNetSpendMap(t.team, this.getClubInsOutsTotal(t.outs) - this.getClubInsOutsTotal(t.ins))} */}
                    {/* {this.setState({ chartData: [...this.state.chartData, {club: t.team, netSpend: this.getClubInsOutsTotal(t.outs) - this.getClubInsOutsTotal(t.ins)}] })} */}
                    {/* {this.addToChartData(t.team, this.getClubInsOutsTotal(t.outs) - this.getClubInsOutsTotal(t.ins))} */}
                  </TableRow>
                </TableBody>
              </Table>
            </TableContainer>
          ))}

          <h2 style={{display: "flex", alignItems: "center", justifyContent: "center"}}>Summary</h2>

          <TableContainer style={{marginBottom: "5%", width: "80%", marginLeft: "10%", display: "flex", alignItems: "center", justifyContent: "center", border: "1px solid", borderColor: "black"}} component={Paper}>
            <Table size="small" style={{width: "100%"}}>
              <TableBody>

                <TableRow style={{backgroundColor: "gray"}}>
                  <TableCell align="center" colSpan={5} style={{fontWeight: 600, color: "white"}}>Top 10 Transfers</TableCell>
                </TableRow>

                <TableRow style={{backgroundColor: "lightgray"}}>
                  <TableCell>Rank</TableCell>
                  <TableCell>Player</TableCell>
                  <TableCell>To</TableCell>
                  <TableCell>From</TableCell>
                  <TableCell>Fee</TableCell>
                </TableRow>

                {[0, 1, 2, 3, 4, 5, 6, 7, 8, 9].map((i) => (
                    <TableRow>
                      <TableCell>{i+1}</TableCell>
                      <TableCell>{Array.from(this.getTopTenTransfers().keys())[i]}</TableCell>
                      <TableCell>{transferToMap.get(Array.from(this.getTopTenTransfers().keys())[i])}</TableCell>
                      <TableCell>{transferFromMap.get(Array.from(this.getTopTenTransfers().keys())[i])}</TableCell>
                      <TableCell>{this.getFeeString(transferFeeMap.get(Array.from(this.getTopTenTransfers().keys())[i]))}</TableCell>
                    </TableRow>
                  ))}

              </TableBody>
            </Table>
          </TableContainer>

          <div style={{display: "flex", alignItems: "center", justifyContent: "center", height: "400px"}}>
            <ResponsiveContainer width="80%" height="100%">
              {/* <h5>hello?</h5> */}
              <BarChart width="70%" height="100%" data={this.getChartData()} barCategoryGap={"15%"}>
                <CartesianGrid />
                <XAxis fontSize={"80%"} dataKey="club" >
                  {/* <Label>Club</Label> */}
                </XAxis>
                <YAxis fontSize={"80%"}>
                  {/* <Label angle={270} position={'insideStart'}>Net Spend (£)</Label> */}
                </YAxis>
                <Tooltip />
                <Legend />
                <Bar dataKey="netSpend" fill="blue" />
              </BarChart>
            </ResponsiveContainer>
          </div>

        </> : <Loader /> }

      </>
    );
  }
}

export default App;
