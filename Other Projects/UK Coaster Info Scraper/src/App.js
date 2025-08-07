import './App.css';
import { Component } from 'react';
import coasterInfo from "./scraping/coaster-info.json"
import { ResponsivePie } from '@nivo/pie';
import { Card, CardMedia, CardContent, CardHeader, Typography } from '@mui/material';
import pbr from "./card-images/Drone-shot-1-1488x1536.jpg"
import bd from "./card-images/BigDipperBlackpoolOldView2.jpg"
import rt from "./card-images/divvtprmd004_978.jpg"
import { BrowserView, MobileView } from 'react-device-detect';
import CoasterDesignBarChart from './components/CoasterDesignBarChart';

class App extends Component {

  state = {
    totalCoasters: 0,
    oldestCoaster: "",
    oldestCoasterDate: "",
    oldestCoasterPark: "",
    mostUsedCoasterName: "",
    mostUsedCoasterNameNo: 0,
    parksWithMostCoasters: [],
    parkWithMostCoastersNo: 0,
    coasterTypeChartData: [],
    coasterDesignChartData: []
  }

  componentDidMount() {
    this.getTotalCoasters();
    this.getMostUsedCoasterName();
    this.getOldestCoaster();
    this.getParkWithMostCoasters();
    this.getCoasterTypeChartData();
    this.getCoasterDesignChartData();
  }

  getTotalCoasters(){
    this.setState( {totalCoasters: coasterInfo.length} )
  }

  getOldestCoaster(){
    coasterInfo.sort( (a, b) => Date.parse(a.time) - Date.parse(b.time) )
    this.setState({
      oldestCoaster: coasterInfo[0].name,
      oldestCoasterDate: coasterInfo[0].time,
      oldestCoasterPark: coasterInfo[0].park
    })
  }

  getMostUsedCoasterName(){
    var coasterNames = [];

    for (let i=0; i < coasterInfo.length; i++){
      coasterNames[i] = coasterInfo[i].name
    }

    var cNameMap = {};
    var mucn = coasterNames[0]
    var maxCount = 1;

    for (let j=0; j < coasterNames.length; j++){
      var cName = coasterNames[j];

      if (cNameMap[cName] == null) {
        cNameMap[cName] = 1;
      } else {
        cNameMap[cName]++;
      }

      if (cNameMap[cName] > maxCount) {
        mucn = cName;
        maxCount = cNameMap[cName];
      }
    }

    this.setState({
      mostUsedCoasterName: mucn,
      mostUsedCoasterNameNo: maxCount
    });
  }

  getParkWithMostCoasters() {
    var parkNames = [];

    for (let i=0; i < coasterInfo.length; i++){
      parkNames[i] = coasterInfo[i].park
    }

    var pNameMap = {};
    var maxCount = 1;
    var pNames = [];

    for (let j=0; j < parkNames.length; j++){
      var pName = parkNames[j];

      if (pNameMap[pName] == null) {
        pNameMap[pName] = 1;
      } else {
        pNameMap[pName]++;
      }

      if (pNameMap[pName] > maxCount) {
        pNames = [pName];
        maxCount = pNameMap[pName];
      } else if (pNameMap[pName] === maxCount && maxCount > 1){
        pNames.push(pName);
        maxCount=pNameMap[pName]
      }
    }

    this.setState({
      parksWithMostCoasters: pNames,
      parkWithMostCoastersNo: maxCount
    });
  }

  getCoasterTypeChartData(){
    var steelCount = 0;
    var woodCount = 0;

    for (let i=0; i < coasterInfo.length; i++){
      if (coasterInfo[i].type === "Steel"){
        steelCount++;
      } else {
        woodCount++;
      }
    }

    var steelChartData = {id: "Steel", label: "Steel", value: steelCount, color: "hsl(125, 87%, 50%)"}
    var woodChartData = {id: "Wood", label: "Wood", value: woodCount, color: "hsl(62, 93%, 49%)"}

    this.setState({coasterTypeChartData: [steelChartData, woodChartData]})
  }

  getCoasterDesignChartData(){
    var coasterDesigns = [];

    for (let i=0; i < coasterInfo.length; i++){
      coasterDesigns[i] = coasterInfo[i].design
    }

    const counts = {};

    for (const des of coasterDesigns){
      counts[des] = counts[des] ? counts[des] + 1 : 1;
    }

    var chartData = [["Design", "Value"]];

    for (let j=0; j < Object.entries(counts).length; j++){
      chartData[j+1] = [Object.keys(counts)[j], Object.values(counts)[j]]
    }

    console.log(chartData);

    this.setState({coasterDesignChartData: chartData});
  }

  render() {
    return (
      <>
        <BrowserView>
          <div style={{height: "140vh", width: "100vw", backgroundColor: "black", position: 'absolute', overflowY: 'hidden'}}>
          
            <div style={{height: "150px", textAlign: 'center', marginBottom: "5px"}}>

              <h1 style={{color: 'white', marginBottom: "-15px"}}>UK Rollercoaster Info</h1>
              <h6 style={{color: 'white', marginBottom: 35}}>Data scraped from rcdb.com</h6>

              <hr style={{color: 'grey', width: '50%', opacity: '25%'}}/>

              <h4 style={{color: 'white', fontSize: "90%"}}>Number of rollercoasters currently operating in the UK: <span style={{color: "yellow", fontWeight: 600}}>{this.state.totalCoasters}</span></h4>
              
            </div>

            <div style={{display: "flex", flexWrap: "wrap", marginBottom: "40px"}}>
              <Card style={{width: "30vw", marginLeft: "2.5vw"}}>
                <CardHeader sx={{textAlign: 'center', backgroundColor: 'grey'}} titleTypographyProps={{variant: 'caption'}} title="UK Theme Park with most rollercoasters is..." />
                <CardMedia component="img" height="170" image={pbr} />
                <CardContent sx={{backgroundColor: "gray", textAlign: 'center'}}>
                  <Typography variant='body2' sx={{color: 'white'}}>{this.state.parksWithMostCoasters[0]} & {this.state.parksWithMostCoasters[1]} with {this.state.parkWithMostCoastersNo} rollercoasters</Typography>
                </CardContent>
              </Card>

              <Card style={{width: "30vw", marginLeft: "2.5vw", marginRight: "2.5vw"}}>
                <CardHeader sx={{textAlign: 'center', backgroundColor: "grey"}} titleTypographyProps={{variant: 'caption'}} title="Oldest operating rollercoaster in the UK is..." />
                <CardMedia component="img" height="170" image={bd} />
                <CardContent sx={{backgroundColor: "gray", textAlign: 'center'}}>
                  <Typography variant='body2' sx={{color: 'white'}}>{this.state.oldestCoaster} from {this.state.oldestCoasterPark} which opened in {this.state.oldestCoasterDate}</Typography>
                </CardContent>
              </Card>

              <Card style={{width: "30vw", marginRight: "2.5vw"}}>
                <CardHeader sx={{textAlign: 'center', backgroundColor: 'grey'}} titleTypographyProps={{variant: 'caption'}} title="Most used rollercoaster name in the UK is..." />
                <CardMedia component="img" height="170" image={rt} />
                <CardContent sx={{backgroundColor: "gray", textAlign: 'center'}}>
                  <Typography variant='body2' sx={{color: 'white'}}>'{this.state.mostUsedCoasterName}' which is the name of {this.state.mostUsedCoasterNameNo} separate UK rollercoasters</Typography>
                </CardContent>
              </Card>
            </div>

            <hr style={{color: 'grey', width: '50%', opacity: '25%'}}/>

            <h5 style={{color: 'white', textAlign: 'center', marginBottom: "25px"}}>The charts below show (left) how many of the UK's coasters are wood vs steel, and (right) how these rollercoasters are designed.</h5>

            <div style={{width: "50vw", height: "350px", paddingBottom: 80}}>

              <ResponsivePie data={this.state.coasterTypeChartData}
                margin={{top: 25, right: 0, left: 0, bottom: 80}}
                innerRadius={0.5}
                padAngle={0.6}
                cornerRadius={2}
                activeOuterRadiusOffset={8}
                colors={{scheme: "pastel2"}}
                arcLinkLabelsTextColor={'white'}
                legends = {[
                    {
                        anchor: 'bottom',
                        direction: 'row',
                        translateX: 10,
                        translateY: 56,
                        itemWidth: 80,
                        itemHeight: 18,
                        symbolShape: 'circle',
                        itemTextColor: 'white'
                    }
                ]}
              />

              <CoasterDesignBarChart chartData={this.state.coasterDesignChartData} />
            </div>

          </div>
        </BrowserView>

        <MobileView>
          <div style={{height: '270vh', width: '100vw', backgroundColor: "black", position: 'absolute', overflowY: 'hidden'}}>

            <div style={{textAlign: 'center'}}>
              <h1 style={{color: 'white', marginBottom: "-15px"}}>UK Rollercoaster Info</h1>
              <h6 style={{color: 'white', marginBottom: 35}}>Data scraped from rcdb.com</h6>

              <hr style={{color: 'grey', width: '50%', opacity: '25%'}}/>

              <h5 style={{color: 'white', fontSize: "80%"}}>Number of rollercoasters currently operating in the UK: <span style={{color: "yellow", fontWeight: 600}}>{this.state.totalCoasters}</span></h5>
            </div>

            <div>
              <Card style={{width: "80vw", marginLeft: "10vw", marginBottom: "20px"}}>
                <CardHeader sx={{textAlign: 'center', backgroundColor: 'grey'}} titleTypographyProps={{variant: 'caption'}} title="UK Theme Park with most rollercoasters is..." />
                <CardMedia component="img" height="170" image={pbr} />
                <CardContent sx={{backgroundColor: "gray", textAlign: 'center'}}>
                  <Typography variant='body2' sx={{color: 'white'}}>{this.state.parksWithMostCoasters[0]} & {this.state.parksWithMostCoasters[1]} with {this.state.parkWithMostCoastersNo} rollercoasters</Typography>
                </CardContent>
              </Card>

              <Card style={{width: "80vw", marginLeft: "10vw", marginBottom: "20px"}}>
                <CardHeader sx={{textAlign: 'center', backgroundColor: "grey"}} titleTypographyProps={{variant: 'caption'}} title="Oldest operating rollercoaster in the UK is..." />
                <CardMedia component="img" height="150" image={bd} />
                <CardContent sx={{backgroundColor: "gray", textAlign: 'center'}}>
                  <Typography variant='body2' sx={{color: 'white'}}>{this.state.oldestCoaster} from {this.state.oldestCoasterPark} which opened in {this.state.oldestCoasterDate}</Typography>
                </CardContent>
              </Card>

              <Card style={{width: "80vw", marginLeft: "10vw", marginBottom: "20px"}}>
                <CardHeader sx={{textAlign: 'center', backgroundColor: 'grey'}} titleTypographyProps={{variant: 'caption'}} title="Most used rollercoaster name in the UK is..." />
                <CardMedia component="img" height="170" image={rt} />
                <CardContent sx={{backgroundColor: "gray", textAlign: 'center'}}>
                  <Typography variant='body2' sx={{color: 'white'}}>'{this.state.mostUsedCoasterName}' which is the name of {this.state.mostUsedCoasterNameNo} UK coasters</Typography>
                </CardContent>
              </Card>
            </div>

            <hr style={{color: 'grey', width: '50%', opacity: '25%'}}/>

            <h5 style={{color: 'white', textAlign: 'center', marginBottom: '40px'}}>UK Rollercoaster Type (Wood or Steel) Pie Chart</h5>

            <div style={{height: "40vh", marginBottom: "80px"}}>
              <ResponsivePie data={this.state.coasterTypeChartData} 
                margin={{top: 25, right: 5, left: 5, bottom: 60}}
                innerRadius={0.5}
                padAngle={0.6}
                cornerRadius={2}
                activeOuterRadiusOffset={8}
                colors={{scheme: "pastel2"}}
                arcLinkLabelsTextColor={'white'}
                legends = {[
                  {
                    anchor: 'bottom',
                    direction: 'row',
                    translateX: 10,
                    translateY: 56,
                    itemWidth: 80,
                    itemHeight: 18,
                    symbolShape: 'circle',
                    itemTextColor: 'white'
                  }
                ]}
              />
            </div>

            <h5 style={{color: 'white', textAlign: 'center', marginBottom: "10px"}}>UK Rollercoaster Design Bar Chart</h5>

            <div style={{justifyContent: 'center', alignItems: 'center', marginTop: "-10px"}}>
              <CoasterDesignBarChart chartData={this.state.coasterDesignChartData} />
            </div>

          </div>
        </MobileView>
      </>
    )
  }

}

export default App;
