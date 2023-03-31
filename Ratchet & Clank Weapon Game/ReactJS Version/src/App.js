import './App.css';
import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import Card from '@mui/material/Card';
import CardMedia from '@mui/material/CardMedia';
import { Autocomplete, Grid, TextField, Dialog, Slide, DialogTitle, DialogContent, DialogContentText, DialogActions, ImageList, ImageListItem, ListSubheader, ImageListItemBar } from '@mui/material';
import Font from 'react-font';
import { Component } from 'react';
import { isBrowser, BrowserView } from 'react-device-detect'
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
import blitzgun from "./images/blitz-gun.jpg"
import bouncer from "./images/bouncer.jpg"
import chopper from "./images/chopper.jpg"
import clankzapper from "./images/clank-zapper.jpg"
import gravitybomb from "./images/gravity-bomb.jpg"
import hoverbombgun from "./images/hoverbomb-gun.jpg"
import lancer from "./images/lancer.jpg"
import lavagun from "./images/lava-gun.jpg"
import minirockettube from "./images/minirocket-tube.jpg"
import miniturretglove from "./images/miniturret-glove.jpg"
import plasmacoil from "./images/plasma-coil.jpg"
import pulserifle from "./images/pulse-rifle.jpg"
import ryno2 from "./images/ryno-2.jpg"
import seekergun from "./images/seeker-gun.jpg"
import sheepinator from "./images/sheepinator.jpg"
import shieldcharger from "./images/shield-charger.jpg"
import spiderbotglove from "./images/spiderbot-glove.jpg"
import synthenoids from "./images/synthenoids.jpg"
import zodiac from "./images/zodiac.jpg"
import b6obliterator from "./images/b6-obliterator.jpg"
import dualvipers from "./images/dual-vipers.jpg"
import fusionrifle from "./images/fusion-rifle.jpg"
import holoshieldlauncher from "./images/holoshield-launcher.jpg"
import hunterminelauncher from "./images/hunter-mine-launcher.jpg"
import magmacannon from "./images/magma-cannon.jpg"
import miniturretlauncher from "./images/mini-turret-launcher.jpg"
import scorpionflail from "./images/scorpion-flail.jpg"
import thearbiter from "./images/the-arbiter.jpg"
import theharbinger from "./images/the-harbinger.jpg"
import chimpomatic from "./images/chimp-o-matic.jpg"
import constructobomb from "./images/constructo-bomb.jpg"
import constructopistol from "./images/constructo-pistol.jpg"
import constructoshotgun from "./images/constructo-shotgun.jpg"
import cryomineglove from "./images/cryomine-glove.jpg"
import dynamoofdoom from "./images/dynamo-of-doom.jpg"
import plasmastriker from "./images/plasma-striker.jpg"
import riftinducer5000 from "./images/rift-inducer-5000.jpg"
import rynov from "./images/ryno-v.jpg"
import soniceruptor from "./images/sonic-eruptor.jpg"
import spiralofdeath from "./images/spiral-of-death.jpg"
import teslaspikes from "./images/tesla-spikes.jpg"
import pixelizer from "./images/pixelizer.jpg"
import protondrum from "./images/proton-drum.jpg"

import RefreshIcon from '@mui/icons-material/Refresh'
import HelpIcon from '@mui/icons-material/Help'

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction='up' ref={ref} {...props} />;
});

class App extends Component {

  state = {
    guess: "",
    guesses: 0,
    weaponsGuessed: ["", "", "", "", "", ""],
    targetWeapon: "",
    hasWon: false,
    hasLost: false,
    showHelp: false,
    deviceContainer: ""
  }

  componentDidMount() {
    if (isBrowser === true){
      this.setState({ deviceContainer: "container" })
    } else {
      this.setState({ deviceContainer: "mobile-container" })
    }

    this.getTargetWeapon();
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
    return (
      <div className={this.state.deviceContainer}>
        <div style={{backgroundColor: "rgba(0, 0, 0, 0.4)"}}>
          <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" style={{backgroundColor: "rgba(245, 242, 0, 0.9)"}}>
              <Toolbar>
                { isBrowser ? 
                <>
                  <RefreshIcon onClick={() => this.refresh()} style={{marginLeft: "5%"}} />

                  <h3 style={{display: "flex", alignItems: "center", justifyContent: "center", width: "80%", marginLeft: "10%", marginRight: "10%", color: "white", fontSize: "90%"}}><Font family="Press Start 2P">Ratchet & Clank Weapon Game</Font></h3>

                  <HelpIcon onClick={() => this.setState({showHelp: true})} style={{marginRight: "5%"}}/>
                </> :
                <>
                  <RefreshIcon onClick={() => this.refresh()} />

                  <h3 style={{display: "flex", alignItems: "center", justifyContent: "center", width: "85%", marginLeft: "7.5%", marginRight: "7.5%", color: "white", fontSize: "50%"}}><Font family="Press Start 2P">Ratchet & Clank Weapon Game</Font></h3>

                  <HelpIcon onClick={() => this.setState({showHelp: true})}/>
                </>
                }

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
                        <>{ isBrowser ?
                          <li>Try to guess correctly within 6 attempts, weapon will be shown on the '?' panel at the end of the game</li> :
                          <li>Try to guess correctly within 6 attempts, weapon will be shown in the pop-up message at the end of the game</li>
                        }</>
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

          <>{ isBrowser ?

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
              </> :

              <ImageList style={{ width: "85%", height: "200px", border: "1px solid", borderColor: "white" }} cols={3} rowHeight={120}>
                <ImageListItem key="Subheader" cols={3} style={{marginBottom: "-25%"}}>
                  <ListSubheader component="div" style={{backgroundColor: "gray"}}>Weapons</ListSubheader>
                </ImageListItem>
                {weaponImages.slice(1).map((item) => (
                  <ImageListItem>
                    <img 
                      src={`${item}`}
                      alt=""
                    />
                    <ImageListItemBar subtitle={weapons[weaponImages.indexOf(item)]}/>
                  </ImageListItem>
                ))}
              </ImageList>

            }
          </>


          </Box>

          <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "2.5%"}}>

            <>{ isBrowser ?
              <Autocomplete 
                disablePortal
                id="guess"
                options={weapons}
                sx={{width: 250, backgroundColor: "white"}}
                onChange={(e) => this.setState({guess: e.target.textContent})}
                onInputChange={(e) => this.handleChange(e)}
                renderInput={(params) => <TextField {...params} label="" />}
              /> :
              <Autocomplete 
                disablePortal
                id="guess"
                options={weapons}
                sx={{width: "62.5%", backgroundColor: "white"}}
                onChange={(e) => this.setState({guess: e.target.textContent})}
                onInputChange={(e) => this.handleChange(e)}
                renderInput={(params) => <TextField {...params} label="" />}
              /> 
            }</>

            <>{ this.state.guess !== "" && weapons.indexOf(this.state.guess) !== -1 && this.state.weaponsGuessed.indexOf(this.state.guess) === -1 ?
              <Button variant="contained" style={{height: 56.1, marginLeft: "-0.5%"}} onClick={() => this.handleSubmit(this.state.guess)}>Submit</Button> :
              <Button variant="contained" style={{height: 56.1, backgroundColor: "lightgray", marginLeft: "-0.5%"}} disabled>Submit</Button>
            }</>

          </Box>

          <>{ isBrowser ?
            <>
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
            </> :

              <>
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
                    <img src={weaponImageMap.get(this.state.targetWeapon)} alt=""/>
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
                    <img src={weaponImageMap.get(this.state.targetWeapon)} alt=""/>
                  </DialogContent>
                  <DialogActions>
                    <Button onClick={() => {this.setState({ hasLost: false }); this.refresh()}}>Play Again</Button>
                  </DialogActions>
                </Dialog>
              </>

          }</>
          
          <BrowserView>
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
          </BrowserView>

          <>{ isBrowser ?

            <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "-0.5%"}}>

              <Grid sx={{ flexGrow: 1 }} container spacing={2}>
                <Grid container justifyContent="center" item xs={12}>
                  {[1, 2, 3, 4, 5, 6].map((value) => (
                    <Guess guessStats={weaponMap.get(this.state.weaponsGuessed[value-1])} targetStats={weaponMap.get(this.state.targetWeapon)}/>
                  ))}
                </Grid>
              </Grid>

            </Box> :

            <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "5%"}}>

            <Grid sx={{ flexGrow: 1 }} container spacing={2}>
              <Grid container justifyContent="center" item xs={12}>
                {[1, 2, 3, 4, 5, 6].map((value) => (
                  <div style={{marginBottom: "7.5%"}}>
                    <h4 style={{marginBottom: "2.5%", color: "white", display: "flex", alignItems: "center", justifyContent: "center"}}>Guess {value}</h4>
                    <Guess guessStats={weaponMap.get(this.state.weaponsGuessed[value-1])} targetStats={weaponMap.get(this.state.targetWeapon)}/>
                  </div>
                ))}
              </Grid>
            </Grid>

            </Box>
          }</>

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
"Blitz Gun",
"Bouncer",
"Chopper",
"Clank Zapper",
"Gravity Bomb",
"Hoverbomb Gun",
"Lancer",
"Lava Gun",
"Minirocket Tube",
"Miniturret Glove",
"Plasma Coil",
"Pulse Rifle",
"RYNO II",
"Seeker Gun",
"Sheepinator",
"Shield Charger",
"Spiderbot Glove",
"Synthenoids",
"Zodiac",
"B6 Obliterator",
"Dual Vipers",
"Fusion Rifle",
"Holoshield Launcher",
"Hunter Mine Launcher",
"Magma Cannon",
"Mini Turret Launcher",
"Scorpion Flail",
"The Arbiter",
"The Harbinger",
"Chimp-o-Matic",
"Constructo Bomb",
"Constructo Pistol",
"Constructo Shotgun",
"CryoMine Glove",
"Dynamo of Doom",
"Plasma Striker",
"Rift Inducer 5000",
"RYNO V",
"Sonic Eruptor",
"Spiral of Death",
"Tesla Spikes",
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
  {console: "PS2", game: "Going Commando", planet: "Oozla", range: "Medium", rof: "Medium", cost: "15000"},
  {console: "PS2", game: "Going Commando", planet: "Tabora", range: "Medium", rof: "Low", cost: "100000"},
  {console: "PS2", game: "Going Commando", planet: "Oozla", range: "Medium", rof: "Medium", cost: "5000"},
  {console: "PS2", game: "Going Commando", planet: "Challenge Mode", range: "Low", rof: "Low", cost: "1000000"},
  {console: "PS2", game: "Going Commando", planet: "Aranos", range: "Medium", rof: "Low", cost: "0"},
  {console: "PS2", game: "Going Commando", planet: "Todano", range: "High", rof: "Low", cost: "120000"},
  {console: "PS2", game: "Going Commando", planet: "Aranos", range: "High", rof: "Medium", cost: "0"},
  {console: "PS2", game: "Going Commando", planet: "Endako", range: "Medium", rof: "High", cost: "25000"},
  {console: "PS2", game: "Going Commando", planet: "Dobbo", range: "High", rof: "High", cost: "50000"},
  {console: "PS2", game: "Going Commando", planet: "Tabora", range: "Medium", rof: "High", cost: "25000"},
  {console: "PS2", game: "Going Commando", planet: "Joba", range: "High", rof: "Low", cost: "150000"},
  {console: "PS2", game: "Going Commando", planet: "Endako", range: "Very High", rof: "Low", cost: "20000"},
  {console: "PS2", game: "Going Commando", planet: "Barlow", range: "High", rof: "High", cost: "1000000"},
  {console: "PS2", game: "Going Commando", planet: "Barlow", range: "High", rof: "Medium", cost: "5000"},
  {console: "PS2", game: "Going Commando", planet: "Todano", range: "Low", rof: "High", cost: "0"},
  {console: "PS2", game: "Going Commando", planet: "Aranos", range: "Very Low", rof: "Low", cost: "100000"},
  {console: "PS2", game: "Going Commando", planet: "Joba", range: "Very High", rof: "Very Low", cost: "15000"},
  {console: "PS2", game: "Going Commando", planet: "Notak", range: "Medium", rof: "Medium", cost: "65000"},
  {console: "PS2", game: "Going Commando", planet: "Aranos", range: "High", rof: "Very Low", cost: "1500000"},
  {console: "PS2", game: "Deadlocked", planet: "Marauder Tournament", range: "Medium", rof: "Low", cost: "15000"},
  {console: "PS2", game: "Deadlocked", planet: "Marauder Tournament", range: "High", rof: "Very High", cost: "0"},
  {console: "PS2", game: "Deadlocked", planet: "Kronos", range: "Very High", rof: "Low", cost: "50000"},
  {console: "PS2", game: "Deadlocked", planet: "Catacrom Four", range: "Low", rof: "Low", cost: "20000"},
  {console: "PS2", game: "Deadlocked", planet: "Sarathos", range: "Medium", rof: "Medium", cost: "35000"},
  {console: "PS2", game: "Deadlocked", planet: "Marauder Tournament", range: "Medium", rof: "Medium", cost: "100000"},
  {console: "PS2", game: "Deadlocked", planet: "Crusader Tournament", range: "Medium", rof: "Medium", cost: "75000"},
  {console: "PS2", game: "Deadlocked", planet: "Orxon", range: "Melee", rof: "Medium", cost: "75000"},
  {console: "PS2", game: "Deadlocked", planet: "Avenger Tournament", range: "High", rof: "Medium", cost: "60000"},
  {console: "PS2", game: "Deadlocked", planet: "Liberator Tournament", range: "Medium", rof: "Low", cost: "2000000"},
  {console: "PS3", game: "A Crack In Time", planet: "Agorian Battleplex", range: "Medium", rof: "High", cost: "0"},
  {console: "PS3", game: "A Crack In Time", planet: "Quantos", range: "Medium", rof: "Medium", cost: "1000"},
  {console: "PS3", game: "A Crack In Time", planet: "Quantos", range: "Medium", rof: "Medium", cost: "0"},
  {console: "PS3", game: "A Crack In Time", planet: "Agorian Battleplex", range: "Medium", rof: "Medium", cost: "0"},
  {console: "PS3", game: "A Crack In Time", planet: "Zanifar", range: "Medium", rof: "Medium", cost: "41000"},
  {console: "PS3", game: "A Crack In Time", planet: "Torren IV", range: "Medium", rof: "Very Low", cost: "16000"},
  {console: "PS3", game: "A Crack In Time", planet: "Lumos", range: "Very High", rof: "Low", cost: "33000"},
  {console: "PS3", game: "A Crack In Time", planet: "Nefarious Space Station", range: "Medium", rof: "Low", cost: "91000"},
  {console: "PS3", game: "A Crack In Time", planet: "Terachnos", range: "High", rof: "Very High", cost: "0"},
  {console: "PS3", game: "A Crack In Time", planet: "Quantos", range: "Medium", rof: "Low", cost: "4000"},
  {console: "PS3", game: "A Crack In Time", planet: "Agorian Battleplex", range: "Medium", rof: "Low", cost: "0"},
  {console: "PS3", game: "A Crack In Time", planet: "Terachnos", range: "Low", rof: "Low", cost: "19000"},
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
  blitzgun,
  bouncer,
  chopper,
  clankzapper,
  gravitybomb,
  hoverbombgun,
  lancer,
  lavagun,
  minirockettube,
  miniturretglove,
  plasmacoil,
  pulserifle,
  ryno2,
  seekergun,
  sheepinator,
  shieldcharger,
  spiderbotglove,
  synthenoids,
  zodiac,
  b6obliterator,
  dualvipers,
  fusionrifle,
  holoshieldlauncher,
  hunterminelauncher,
  magmacannon,
  miniturretlauncher,
  scorpionflail,
  thearbiter,
  theharbinger,
  chimpomatic,
  constructobomb,
  constructopistol,
  constructoshotgun,
  cryomineglove,
  dynamoofdoom,
  plasmastriker,
  riftinducer5000,
  rynov,
  soniceruptor,
  spiralofdeath,
  teslaspikes,
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