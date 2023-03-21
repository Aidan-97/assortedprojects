import './App.css';
import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import { Autocomplete, Grid, TextField, Dialog, Slide, DialogTitle, DialogContent, DialogContentText, DialogActions } from '@mui/material';
import Font from 'react-font';
import { Component } from 'react';
import Guess from './components/Guess';

import bombglove from "./images/bomb-glove.jpg"
import pyrocitor from "./images/pyrocitor.jpg"
import blaster from "./images/blaster.jpg"
import gloveofdoom from "./images/glove-of-doom.jpg"
import suckcannon from "./images/suck-cannon.jpg"
import taunter from "./images/taunter.jpg"
import mineglove from "./images/mine-glove.jpg"
import ryno from "./images/ryno.jpg"
import devastator from "./images/devastator.jpg"
import walloper from "./images/walloper.jpg"
import visibombgun from "./images/visibomb-gun.jpg"
import decoyglove from "./images/decoy-glove.jpg"
import dronedevice from "./images/drone-device.jpg"
import morphoray from "./images/morph-o-ray.jpg"
import teslaclaw from "./images/tesla-claw.jpg"
import pixelizer from "./images/pixelizer.jpg"
import protondrum from "./images/proton-drum.jpg"

import background from "./images/background.jpg"
import background2 from "./images/background2.jpg"
import background3 from "./images/background3.jpg"
import background4 from "./images/background4.jpg"
import background5 from "./images/background5.jpg"

import RefreshIcon from '@mui/icons-material/Refresh'
import HelpIcon from '@mui/icons-material/Help'

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction='up' ref={ref} {...props} />;
});

var backgrounds = [background, background2, background3, background4, background5];

class App extends Component {

  state = {
    guess: "",
    guesses: 0,
    weaponsGuessed: ["", "", "", "", "", ""],
    targetWeapon: "",
    hasWon: false,
    hasLost: false,
    showHelp: false,
    backgroundImage: 0
  }

  componentDidMount() {
    this.getTargetWeapon();
    this.interval = setInterval(() => this.changeBackgroundImage(), 5000);
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }

  changeBackgroundImage() {
    let newCurrentImg = 0;
    const noOfImgs = backgrounds.length;

    if (this.state.backgroundImage !== noOfImgs - 1){
      newCurrentImg = this.state.backgroundImage + 1;
    }

    this.setState({ backgroundImage: newCurrentImg })
  }

  getTargetWeapon() {
    const weaponSelection = weapons.slice(1);
    const randomWeapon = weaponSelection[Math.floor(Math.random() * weaponSelection.length)]

    this.setState({ targetWeapon: randomWeapon })
  }

  handleChange = (e) => {
    this.setState({
      [e.target.id]: e.target.value
    })
  }

  handleSubmit(currentGuess){
    this.setState({ guesses: this.state.guesses + 1 })

    const newWeaponsGuessed = this.state.weaponsGuessed.slice()
    newWeaponsGuessed[this.state.guesses] = currentGuess
    this.setState({ weaponsGuessed: newWeaponsGuessed })

    this.setState({ guess: "" })

    this.checkWin(currentGuess, this.state.targetWeapon)
  }

  checkWin(guess, target) {
    if (guess === target){
      this.setState({ hasWon: true })
    } else if (guess !== target && this.state.guesses === 5){
      this.setState({ hasLost: true })
    }
  }

  refresh(){
    this.setState({
      guess: "",
      guesses: 0,
      weaponsGuessed: ["", "", "", "", "", ""],
      hasWon: false,
      hasLost: false
    })

    this.getTargetWeapon()
  }

  render() {
    const imageURL = `url('${backgrounds[this.state.backgroundImage]}')`;

    return (
      <div className="container" style={{backgroundImage: imageURL}}>
        <div style={{backgroundColor: "rgba(0, 0, 0, 0.7)"}}>
          <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" style={{backgroundColor: "rgba(245, 242, 0, 0.9)"}}>
              <Toolbar>

                <RefreshIcon onClick={() => this.refresh()} style={{marginLeft: "5%"}} />

                <h3 style={{display: "flex", alignItems: "center", justifyContent: "center", width: "80%", marginLeft: "10%", marginRight: "10%", color: "white", fontSize: "90%"}}><Font family="Press Start 2P">Ratchet & Clank Weapon Game</Font></h3>

                <HelpIcon onClick={() => this.setState({showHelp: true})} style={{marginRight: "5%"}}/>

                  <Dialog
                    open={this.state.showHelp}
                    TransitionComponent={Transition}
                    keepMounted
                    onClose={() => this.setState({showHelp: !this.state.showHelp})}
                    aria-describedby="alert-dialog-slide-description"
                  >
                    <DialogTitle>{"Help"}</DialogTitle>
                    <DialogContent>
                      <DialogContentText id="alert-dialog-slide-description">
                        <li>Select a weapon from the autocomplete/drop-down input box and press 'Submit' button to enter guess</li>
                        <li>For each guess, the grid will show in how many of the 6 categories your guessed weapon matches the target weapon</li>
                        <li>Try to guess correctly within 6 attempts, weapon will be shown on the '?' panel at the end of the game</li>
                      </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                      <Button onClick={() => this.setState({ showHelp: false })}>Close</Button>
                    </DialogActions>
                  </Dialog>
                
              </Toolbar>
            </AppBar>
          </Box>

          <Box sx={{ minWidth: 275 }} style={{display: "flex", alignItems: "center", justifyContent: "center"}}>

            <>{this.state.hasWon === true ?
              <Card variant="outlined" style={{marginTop: "40px", height: "250px", width: "400px", padding: "10px"}}>
                <CardMedia
                  component="img"
                  height="245px"
                  image={weaponImageMap.get(this.state.targetWeapon)}
                />
              </Card>
              :
              <Card variant="outlined" style={{backgroundColor: "gray", marginTop: "40px", height: "250px", width: "400px"}}>
                <div style={{display: "flex", fontSize: "125px", fontWeight: 600, justifyContent: "center", marginTop: "35px"}}>?</div>
              </Card>}
            </>

          </Box>

          <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "2.5%"}}>

            <Autocomplete 
              disablePortal
              id="guess"
              options={weapons}
              sx={{width: 250, backgroundColor: "white"}}
              onChange={(e) => this.setState({guess: e.target.textContent})}
              onInputChange={(e) => this.handleChange(e)}
              renderInput={(params) => <TextField {...params} label="" />}
            />

            <>{ this.state.guess !== "" && weapons.indexOf(this.state.guess) !== -1 && this.state.weaponsGuessed.indexOf(this.state.guess) === -1 ?
              <Button variant="contained" style={{height: 56.1, marginLeft: "-0.5%"}} onClick={() => this.handleSubmit(this.state.guess)}>Submit</Button> :
              <Button variant="contained" style={{height: 56.1, backgroundColor: "lightgray", marginLeft: "-0.5%"}} disabled>Submit</Button>
            }</>

          <Dialog 
            open={this.state.hasWon}
            TransitionComponent={Transition}
            keepMounted
            onClose={(_, reason) => {
              if (reason !== "backdropClick") {
                this.setState({hasWon: !this.state.hasWon})
              }
            }}
            aria-describedby="alert-dialog-slide-description"
          >
            <DialogTitle>{"Congratulations!"}</DialogTitle>
            <DialogContent>
              <DialogContentText id="alert-dialog-slide-description">
                Correct! Answer was {this.state.targetWeapon}
              </DialogContentText>
            </DialogContent>
            <DialogActions>
              <Button onClick={() => {this.setState({ hasWon: false }); this.refresh()}}>Play Again</Button>
            </DialogActions>
          </Dialog>

          <Dialog 
            open={this.state.hasLost}
            TransitionComponent={Transition}
            keepMounted
            onClose={(_, reason) => {
              if (reason !== "backdropClick") {
                this.setState({hasLost: !this.state.hasLost})
              }
            }}
            aria-describedby="alert-dialog-slide-description"
          >
            <DialogTitle>{"Unlucky!"}</DialogTitle>
            <DialogContent>
              <DialogContentText id="alert-dialog-slide-description">
                Incorrect! Answer was {this.state.targetWeapon}
              </DialogContentText>
            </DialogContent>
            <DialogActions>
              <Button onClick={() => {this.setState({ hasLost: false }); this.refresh()}}>Play Again</Button>
            </DialogActions>
          </Dialog>

          </Box>

          <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "2.5%", marginBottom: "1%"}}>
            <Grid style={{display: "flex", alignItems: "center", justifyContent: "center"}} container spacing={2}>
              <Grid item>
                <div style={{width: 100, textAlign: "center", color: "white"}}>Console</div>
              </Grid>
              <Grid item>
                <div style={{width: 100, textAlign: "center", color: "white"}}>Game</div>
              </Grid>
              <Grid item>
                <div style={{width: 100, textAlign: "center", color: "white"}}>Planet/Level</div>
              </Grid>
              <Grid item>
                <div style={{width: 100, textAlign: "center", color: "white"}}>Range</div>
              </Grid>
              <Grid item>
                <div style={{width: 100, textAlign: "center", color: "white"}}>Rate of Fire</div>
              </Grid>
              <Grid item>
                <div style={{width: 100, textAlign: "center", color: "white"}}>Cost (Bolts)</div>
              </Grid>
            </Grid>
          </Box>

          <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "-0.5%"}}>

            <Grid sx={{ flexGrow: 1 }} container spacing={2}>
              <Grid container justifyContent="center" item xs={12}>
                {[1, 2, 3, 4, 5, 6].map((value) => (
                  <Guess guessStats={weaponMap.get(this.state.weaponsGuessed[value-1])} targetStats={weaponMap.get(this.state.targetWeapon)}/>
                ))}
              </Grid>
            </Grid>

          </Box>
        </div>
      </div>
    );
  }
}

const weapons = [ "",
"Bomb Glove",
"Pyrocitor",
"Blaster",
"Glove of Doom",
"Suck Cannon",
"Taunter",
"Mine Glove",
"RYNO",
"Devastator",
"Walloper",
"Visibomb Gun",
"Decoy Glove",
"Drone Device",
"Morph-o-ray",
"Tesla Claw",
"Pixeliser",
"Proton Drum"];

const weaponStats = [
  {console: "...", game: "...", planet: "...", range: "...", rof: "...", cost: "..."},
  {console: "PS2", game: "Ratchet & Clank", planet: "Veldin", range: "Medium", rof: "Medium", cost: "0"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Novalis", range: "Low", rof: "High", cost: "2500"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Kerwan", range: "Medium", rof: "High", cost: "2500"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Eudora", range: "Low", rof: "Low", cost: "7500"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Eudora", range: "Medium", rof: "High", cost: "0"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Nebula G34", range: "Medium", rof: "High", cost: "2500"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Rilgar", range: "Low", rof: "Low", cost: "7500"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Rilgar", range: "High", rof: "Medium", cost: "150000"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Batalia", range: "High", rof: "Medium", cost: "10000"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Gaspar", range: "Melee", rof: "Low", cost: "7500"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Orxon", range: "Very High", rof: "Very Low", cost: "15000"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Pokitaru", range: "Medium", rof: "Low", cost: "7500"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Hoven", range: "Low", rof: "Low", cost: "7500"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Oltanis", range: "Medium", rof: "High", cost: "0"},
  {console: "PS2", game: "Ratchet & Clank", planet: "Oltanis", range: "Medium", rof: "High", cost: "40000"},
  {console: "PS4", game: "Ratchet & Clank 2016", planet: "Rilgar/Nebula G34", range: "Medium", rof: "Low", cost: "15000"},
  {console: "PS4", game: "Ratchet & Clank 2016", planet: "Novalis", range: "Medium", rof: "Low", cost: "4000"},
];

const weaponImages = ["",
  bombglove,
  pyrocitor,
  blaster,
  gloveofdoom,
  suckcannon,
  taunter,
  mineglove,
  ryno,
  devastator,
  walloper,
  visibombgun,
  decoyglove,
  dronedevice,
  morphoray,
  teslaclaw,
  pixelizer,
  protondrum
];

const weaponMap = new Map();
const weaponImageMap = new Map();

for (let i=0; i< weapons.length; i++){
  weaponMap.set(weapons[i], weaponStats[i])
  weaponImageMap.set(weapons[i], weaponImages[i])
}

export default App;