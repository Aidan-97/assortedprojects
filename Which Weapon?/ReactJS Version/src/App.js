import './App.css';
import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { Grid, Dialog, Slide, DialogTitle, DialogContent, DialogContentText, DialogActions } from '@mui/material';
import Font from 'react-font';
import { Component } from 'react';
import { isBrowser, BrowserView } from 'react-device-detect'
import Guess from './components/Guess';
import ImageListDisplay from './components/ImageListDisplay';
import { weapons, weaponImages, weaponStats } from './components/WeaponArrays';
import RefreshIcon from '@mui/icons-material/Refresh'
import HelpIcon from '@mui/icons-material/Help'
import InfoIcon from '@mui/icons-material/Info'
import headerIcon from "./images/icon-header.png"
import "./fonts/simiandisplay-orangutan.otf"

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction='up' ref={ref} {...props} />;
});

class App extends Component {

  state = {
    guesses: 0,
    weaponsGuessed: ["", "", "", "", "", ""],
    guessesFlipped: [false, false, false, false, false, false],
    targetWeapon: "",
    hasWon: false,
    hasLost: false,
    isGameOver: false,
    showHelp: false,
    showDisclaimer: false,
    deviceContainer: "",
    imageLists: []
  }

  componentDidMount() {
    if (isBrowser === true){
      this.setState({ deviceContainer: "container" })
    } else {
      this.setState({ deviceContainer: "mobile-container" })
    }

    this.getTotalImageLists();
    this.getTargetWeapon();
  }

  getTotalImageLists() {
    const weaponGames = []
  
    for (let i=0; i < weaponStats.slice(1).length; i++){
      weaponGames.push(weaponStats.slice(1)[i].game)
    }

    const uniqueGames = [...new Set(weaponGames)]

    this.setState({ imageLists: uniqueGames })
  }

  getTargetWeapon() {
    const weaponSelection = weapons.slice(1);
    const randomWeapon = weaponSelection[Math.floor(Math.random() * weaponSelection.length)]

    this.setState({ targetWeapon: randomWeapon })
  }

  // method for dealing with submitted guess: checks/changes listed in comments below
  handleSubmit(currentGuess){
    if (!weapons.includes(currentGuess) || this.state.weaponsGuessed.includes(currentGuess) || this.state.guesses >= 6 || this.state.isGameOver) {
      return
    }

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

    if (currentGuess === this.state.targetWeapon || this.state.guesses === 5) {
      this.setState({ isGameOver: true })
    }

    // scroll list of guesses on mobile to show most recent guess, if not on first guess
    this.scrollMobileGuesses(this.state.guesses)

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
        guesses: 0,
        weaponsGuessed: ["", "", "", "", "", ""],
        guessesFlipped: [false, false, false, false, false, false],
        hasWon: false,
        hasLost: false,
        isGameOver: false
      })

    this.scrollMobileGuesses(0)
    this.getTargetWeapon()
  }

  // for when refresh icon in header is clicked, user prompted to confirm they want to restart first before refreshing
  earlyRefresh(){
    const isConfirmed = window.confirm("Are you sure you want to restart?")

    if (isConfirmed) {
      this.refresh();
    }
  }

  // method to scroll list of guesses on mobile to show most recent guess, if not on first guess
  scrollMobileGuesses(guessNo) {
    const mobileGuessScroll = document.getElementById("mobile-guess-scroll");
    if (this.state.deviceContainer === "mobile-container") {
      if (guessNo !== 0) {
        setTimeout( () => {mobileGuessScroll.scrollBy({ top: (mobileGuessScroll.scrollHeight / 6) * guessNo, behavior: "smooth" })}, 1000)
      } else {
        mobileGuessScroll.scrollTop = 0
      }
    }
  }

  render() {
    return (
      <div className={this.state.deviceContainer}>
        <div style={{backgroundColor: "rgba(0, 0, 0))"}}>
          <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static" style={{backgroundColor: "rgba(245, 114, 0, 0.9)"}}>
              <Toolbar>

                {/** 'isBrowser' from 'react-device-detect' npm package used here to alter display of toolbar on browser vs on mobile, used on multiple elements in this program */}

                { isBrowser ? 
                <>
                  <RefreshIcon onClick={() => this.earlyRefresh()} style={{marginLeft: "5%"}} />

                  <h3 style={{display: "flex", alignItems: "center", justifyContent: "center", width: "80%", marginLeft: "11.5%", marginRight: "10%", color: "white", fontSize: "90%"}}><Font family="Press Start 2P">Which</Font><img style={{width: "40px", marginRight: "5px"}} alt="" src={headerIcon}/><Font family="Press Start 2P">Weapon?</Font></h3>

                  <InfoIcon onClick={() => this.setState({showDisclaimer: true})} style={{marginRight: "1.5%"}}/>

                  <HelpIcon onClick={() => this.setState({showHelp: true})} style={{marginRight: "5%"}}/>
                </> :
                <>
                  <RefreshIcon onClick={() => this.earlyRefresh()} />

                  <h3 style={{display: "flex", alignItems: "center", justifyContent: "center", width: "85%", marginLeft: "20%", marginRight: "7.5%", color: "white", fontSize: "60%"}}><Font family="Press Start 2P">Which</Font><img style={{width: "40px", marginRight: "5px"}} alt="" src={headerIcon}/><Font family="Press Start 2P">Weapon?</Font></h3>

                  <InfoIcon onClick={() => this.setState({showDisclaimer: true})} style={{marginRight: "1.5%"}}/>

                  <HelpIcon onClick={() => this.setState({showHelp: true})}/>
                </>
                }

                {/** Disclaimer pop-up */}
                  <Dialog
                    open={this.state.showDisclaimer}
                    TransitionComponent={Transition}
                    keepMounted
                    onClose={() => this.setState({showDisclaimer: !this.state.showDisclaimer})}
                    aria-describedby='alert-dialog-slide-description'
                  >
                    <DialogTitle style={{textAlign: "center"}}>{"Disclaimer"}</DialogTitle>
                    <DialogContent>
                      <DialogContentText>
                        <li>'Ratchet & Clank' and all game titles, weapon names & images are the property of Insomniac</li><br/>
                        <li>Weapon stats taken from Ratchet & Clank Wiki</li><br/>
                        <li>General game structure inspired by Wordle, property of NY Times</li><br/>
                        {isBrowser ? <li>Background image taken from 'Ratchet & Clank: Up Your Arsenal', also the property of Insomniac</li> : <li>Background image created by Reddit user 'LeimBR'</li>}
                      </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                      <Button onClick={() => this.setState({ showDisclaimer: false })}>Close</Button>
                    </DialogActions>
                  </Dialog>

                 {/** Help pop-up */}
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
                        <li>Click a weapon image to submit your guess</li><br/>
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

          <Box style={{display: "flex", alignItems: "center", justifyContent: "center", minWidth: 275}}>
            <ImageListDisplay
              weaponNames={weapons}
              weaponImages={weaponImages}
              weaponStats={weaponStats}
              imageLists={this.state.imageLists}
              guessedWeapons={this.state.weaponsGuessed}
              isGameOver={this.state.isGameOver}
              onWeaponSelect={(weaponName) => this.handleSubmit(weaponName)}
            />
          </Box>

            <>
              {/** Win game pop-up */}
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
                sx={{alignItems: "center", justifyContent: "center"}}
              >
                <DialogTitle>{"Congratulations!"}</DialogTitle>
                <DialogContent>
                  <DialogContentText id="alert-dialog-slide-description">
                    Correct! Answer was {this.state.targetWeapon}
                  </DialogContentText>
                  {/* <img src={weaponImageMap.get(this.state.targetWeapon)} alt=""/> */}
                </DialogContent>
                <DialogActions>
                  <Button onClick={() => {this.setState({ hasWon: false }); this.refresh()}}>Play Again</Button>
                </DialogActions>
              </Dialog>

              {/** Lose game pop-up */}
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
                  {/* <img src={weaponImageMap.get(this.state.targetWeapon)} alt=""> */}
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

          {/** 'isBrowser used again, this time to create 6x6 grid of guess panels on browser vs 6 separate 3x2 grids of panels on mobile.'
           *    Extra div created on mobile view to allow scrolling of just the guess grids (i.e. just the bottom half of the screen) while
           *    keeping top half of screen fixed for improved UX, orange scrollbar added to scrollable div to make scrollable area clear &
           *    to match banner colour.
           */}

          <>{ isBrowser ?

            <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "-0.5%"}}>

              <Grid sx={{ flexGrow: 1}} container spacing={2}>
                <Grid container justifyContent="center" item xs={12}>
                  {[1, 2, 3, 4, 5, 6].map((value) => (
                      <Guess guessStats={weaponMap.get(this.state.weaponsGuessed[value-1])} targetStats={weaponMap.get(this.state.targetWeapon)} isFlipped={this.state.guessesFlipped[value-1]} />
                  ))}
                </Grid>
              </Grid>

            </Box> :

            <div id="mobile-guess-scroll" style={{overflowY: "scroll", height: "51vh", scrollbarWidth: "thin", scrollbarColor: "rgba(245, 114, 0, 0.9) rgba(0, 0, 0, 0.4)"}}>
              <Box style={{display: "flex", alignItems: "center", justifyContent: "center", marginTop: "5%"}}>

              <Grid sx={{ flexGrow: 1 }} container spacing={2}>
                <Grid container justifyContent="center" item xs={12}>
                  {[1, 2, 3, 4, 5, 6].map((value) => (
                    <div style={{marginBottom: "7.5%"}}>
                      <h4 style={{marginBottom: "2.5%", color: "white", display: "flex", alignItems: "center", justifyContent: "center", fontFamily: "SD"}}>Guess {value}: {this.state.weaponsGuessed[value-1]}</h4>
                      <Guess guessStats={weaponMap.get(this.state.weaponsGuessed[value-1])} targetStats={weaponMap.get(this.state.targetWeapon)} isFlipped={this.state.guessesFlipped[value-1]}/>
                    </div>
                  ))}
                </Grid>
              </Grid>

              </Box>
            </div>
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