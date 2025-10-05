import './App.css';
import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { Autocomplete, Grid, TextField, Dialog, Slide, DialogTitle, DialogContent, DialogContentText, DialogActions } from '@mui/material';
import Font from 'react-font';
import { Component } from 'react';
import { isBrowser, BrowserView } from 'react-device-detect'
import Guess from './components/Guess';
import ImageListDisplay from './components/ImageListDisplay';
import { weapons, weaponImages, weaponStats } from './components/WeaponArrays';
import RefreshIcon from '@mui/icons-material/Refresh'
import HelpIcon from '@mui/icons-material/Help'
import headerIcon from "./images/icon-header.png"
import "./fonts/simiandisplay-orangutan.otf"

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction='up' ref={ref} {...props} />;
});

class App extends Component {

  state = {
    guess: "",
    guesses: 0,
    weaponsGuessed: ["", "", "", "", "", ""],
    guessesFlipped: [false, false, false, false, false, false],
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

  // method for dealing with submitted guess: checks/changes listed in comments below
  handleSubmit(currentGuess){
    // increment no. of guesses by 1
    this.setState({ guesses: this.state.guesses + 1 })

    // add newly guessed weapon to weaponsGuessed array to track all guesses & prevent same weapon being guessed again
    const newWeaponsGuessed = this.state.weaponsGuessed.slice()
    newWeaponsGuessed[this.state.guesses] = currentGuess
    this.setState({ weaponsGuessed: newWeaponsGuessed })

    // flip panels for corresponding guess no.
    const newGuessesFlipped = this.state.guessesFlipped.slice()
    newGuessesFlipped[this.state.guesses] = true
    this.setState({ guessesFlipped: newGuessesFlipped })

    // reset guess value
    this.setState({ guess: "" })

    // check if guessed weapon matches target weapon
    this.checkWin(currentGuess, this.state.targetWeapon)
  }

  checkWin(guess, target) {
    if (guess === target){
      setTimeout( () => {this.setState({ hasWon: true })}, 800) // delay added to allow full panel flipping animation to play before win/lose pop-up message
    } else if (guess !== target && this.state.guesses === 5){
      this.setState({ hasLost: true })
    }
  }

  // basic reset after game won or lost
  refresh(){
    this.setState({
        guess: "",
        guesses: 0,
        weaponsGuessed: ["", "", "", "", "", ""],
        guessesFlipped: [false, false, false, false, false, false],
        hasWon: false,
        hasLost: false
      })

    this.getTargetWeapon()
  }

  // for when refresh icon in header is clicked, user prompted to confirm they want to restart first before refreshing
  earlyRefresh(){
    const isConfirmed = window.confirm("Are you sure you want to restart?")

    if (isConfirmed) {
      this.refresh();
    }
  }

  render() {
    return (
      <div className={this.state.deviceContainer}>
        <div style={{backgroundColor: "rgba(0, 0, 0, 0.4)"}}>
          <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" style={{backgroundColor: "rgba(245, 242, 0, 0.9)"}}>
              <Toolbar>

                {/** 'isBrowser' from 'react-device-detect' npm package used here to alter display of toolbar on browser vs on mobile, used on multiple elements in this program */}

                { isBrowser ? 
                <>
                  <RefreshIcon onClick={() => this.earlyRefresh()} style={{marginLeft: "5%"}} />

                  <h3 style={{display: "flex", alignItems: "center", justifyContent: "center", width: "80%", marginLeft: "10%", marginRight: "10%", color: "white", fontSize: "90%"}}><Font family="Press Start 2P">Which</Font><img style={{width: "40px", marginRight: "5px"}} alt="" src={headerIcon}/><Font family="Press Start 2P">Weapon?</Font></h3>

                  <HelpIcon onClick={() => this.setState({showHelp: true})} style={{marginRight: "5%"}}/>
                </> :
                <>
                  <RefreshIcon onClick={() => this.earlyRefresh()} />

                  <h3 style={{display: "flex", alignItems: "center", justifyContent: "center", width: "85%", marginLeft: "7.5%", marginRight: "7.5%", color: "white", fontSize: "60%"}}><Font family="Press Start 2P">Which</Font><img style={{width: "40px", marginRight: "5px"}} alt="" src={headerIcon}/><Font family="Press Start 2P">Weapon?</Font></h3>

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
                    <DialogTitle style={{textAlign: "center"}}>{"Help"}</DialogTitle>
                    <DialogContent>
                      <DialogContentText id="alert-dialog-slide-description">
                        <h4>How to Play</h4>
                        <li>Select a weapon from the autocomplete/drop-down input box and press 'Submit' button to enter guess</li><br/>
                        <li>For each guess, the grid will show in how many of the 6 categories your guessed weapon matches the target weapon</li><br/>
                        <li>Try to guess correctly within 6 attempts, the correct weapon will be shown in the pop-up message at the end of the game</li>
                        <h4>Reminders</h4>
                        <li>For deployable turret-type weapons (e.g. the Miniturret Glove) range, and rate of fire refer to the turret, not the glove</li><br/>
                        <li>Since cost refers ONLY to bolts, weapons earned by finding schematics (e.g. RYNO V) or via battle arena challenges (e.g. Constructo Shotgun) will have a cost of 0, as will weapons that the player starts each game with</li>
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
            <ImageListDisplay weaponNames={weapons} weaponImages={weaponImages} />
          </Box>

          <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "2.5%"}}>

            <>{ isBrowser ?
              <Autocomplete 
                disablePortal
                id="guess"
                options={weapons.slice(1)}
                sx={{width: 250, backgroundColor: "white", border: "-1.5px solid", borderColor: "white", borderRadius: "4px", "& .MuiOutlinedInput-root.Mui-focused .MuiOutlinedInput-notchedOutline": {borderColor: "rgb(255, 255, 255, 0.1)"}}}
                onChange={(e) => this.setState({guess: e.target.textContent})}
                onInputChange={(e) => this.handleChange(e)}
                renderInput={(params) => <TextField {...params} label="" />}
              /> :
              <Autocomplete 
                disablePortal
                id="guess"
                options={weapons.slice(1)}
                sx={{width: "62.5%", backgroundColor: "white", border: "-1.5px solid", borderColor: "white", borderRadius: "4px", "& .MuiOutlinedInput-root.Mui-focused .MuiOutlinedInput-notchedOutline": {borderColor: "rgb(255, 255, 255, 0.1)"}}}
                onChange={(e) => this.setState({guess: e.target.textContent})}
                onInputChange={(e) => this.handleChange(e)}
                renderInput={(params) => <TextField {...params} label="" />}
              /> 
            }</>

            {/** Submit button only activated if properly entered, newly guessed weapon is in the autocomplete input box */}

            <>{ this.state.guess !== "" && weapons.indexOf(this.state.guess) !== -1 && this.state.weaponsGuessed.indexOf(this.state.guess) === -1 ?
              <Button variant="contained" style={{height: 56, marginLeft: "-5px", backgroundColor: "rgb(245, 242, 0)", fontWeight: "bolder", color: "black"}} onClick={() => this.handleSubmit(this.state.guess)}>Submit</Button> :
              <Button variant="contained" style={{height: 56, backgroundColor: "lightgray", marginLeft: "-5px"}} disabled>Submit</Button>
            }</>

          </Box>

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
          
          {/** Similar to use of 'isBrowser', elements in '<BrowserView> tags are only rendered on browser, used here to create row of labels for grid of guesses as shown on browser */}

          <BrowserView>
            <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "2.5%", marginBottom: "1%"}}>
              <Grid style={{display: "flex", alignItems: "center", justifyContent: "center"}} container spacing={2}>
                <Grid item>
                  <div style={{width: 100, textAlign: "center", color: "white", fontFamily: "SD", fontSize: "115%"}}>Console</div>
                </Grid>
                <Grid item>
                  <div style={{width: 100, textAlign: "center", color: "white", fontFamily: "SD", fontSize: "115%"}}>Game</div>
                </Grid>
                <Grid item>
                  <div style={{width: 100, textAlign: "center", color: "white", fontFamily: "SD", fontSize: "115%"}}>Planet/Level</div>
                </Grid>
                <Grid item>
                  <div style={{width: 100, textAlign: "center", color: "white", fontFamily: "SD", fontSize: "115%"}}>Range</div>
                </Grid>
                <Grid item>
                  <div style={{width: 100, textAlign: "center", color: "white", fontFamily: "SD", fontSize: "115%"}}>Rate of Fire</div>
                </Grid>
                <Grid item>
                  <div style={{width: 100, textAlign: "center", color: "white", fontFamily: "SD", fontSize: "115%"}}>Cost (Bolts)</div>
                </Grid>
              </Grid>
            </Box>
          </BrowserView>

          {/** 'isBrowser used again, this time to create 6x6 grid of guess panels on browser vs 6 separate 3x2 grids of panels on mobile */}

          <>{ isBrowser ?

            <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "-0.5%"}}>

              <Grid sx={{ flexGrow: 1 }} container spacing={2}>
                <Grid container justifyContent="center" item xs={12}>
                  {[1, 2, 3, 4, 5, 6].map((value) => (
                    <Guess guessStats={weaponMap.get(this.state.weaponsGuessed[value-1])} targetStats={weaponMap.get(this.state.targetWeapon)} isFlipped={this.state.guessesFlipped[value-1]}/>
                  ))}
                </Grid>
              </Grid>

            </Box> :

            <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "5%"}}>

            <Grid sx={{ flexGrow: 1 }} container spacing={2}>
              <Grid container justifyContent="center" item xs={12}>
                {[1, 2, 3, 4, 5, 6].map((value) => (
                  <div style={{marginBottom: "7.5%"}}>
                    <h4 style={{marginBottom: "2.5%", color: "white", display: "flex", alignItems: "center", justifyContent: "center", fontFamily: "SD"}}>Guess {value}</h4>
                    <Guess guessStats={weaponMap.get(this.state.weaponsGuessed[value-1])} targetStats={weaponMap.get(this.state.targetWeapon)} isFlipped={this.state.guessesFlipped[value-1]}/>
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

// weapons mapped to their stats & images below, with values for weapon names, stats & images all imported from 'weaponArrays' file

const weaponMap = new Map();
const weaponImageMap = new Map();

for (let i=0; i< weapons.length; i++){
  weaponMap.set(weapons[i], weaponStats[i])
  weaponImageMap.set(weapons[i], weaponImages[i])
}

export default App;
