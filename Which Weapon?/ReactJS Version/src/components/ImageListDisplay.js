import { ImageList, ImageListItem, ListSubheader, ImageListItemBar, IconButton, ButtonBase } from "@mui/material"
import { isBrowser } from "react-device-detect"
import { useState } from "react"
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft"
import KeyboardArrowRightIcon from "@mui/icons-material/KeyboardArrowRight"

// Single image list changed to separate image lists for each game, with pagination buttons to switch between games
// Image list also now acts as buttons to enter guesses, images greyed out and disabled as buttons after guess to prevent duplicate guesses

export default function ImageListDisplay({weaponNames, weaponImages, weaponStats, imageLists, guessedWeapons, isGameOver, onWeaponSelect}){
    const [page, setPage] = useState(1)
    const currentGame = imageLists[page - 1]
    const imageListItems = weaponNames.slice(1).map((weaponName, index) => ({
        weaponName,
        image: weaponImages[index + 1],
        game: weaponStats[index + 1].game,
        index: index + 1
    })).filter((item) => item.game === currentGame)

    return (
        <div style={{display: "flex", flexDirection: "column", alignItems: "center", width: isBrowser ? "100vw" : undefined}}>
          {isBrowser ? (
            <>
              <ListSubheader component="div" style={{width: "100%", boxSizing: "border-box", backgroundColor: "gray", display: "flex", alignItems: "center", justifyContent: "space-between", fontWeight: "bolder", color:"black", fontFamily: "SD", fontSize: "125%"}}>
                  <IconButton size="small" aria-label="Previous game" disabled={page === 1} onClick={() => setPage(page - 1)}>
                    <KeyboardArrowLeftIcon />
                  </IconButton>
                  {currentGame}
                  <IconButton size="small" aria-label="Next game" disabled={page === imageLists.length} onClick={() => setPage(page + 1)}>
                    <KeyboardArrowRightIcon />
                  </IconButton>
              </ListSubheader>
              <ImageList
                style={{width: "100vw", height: "130px", margin: 0, border: "1px solid", borderColor: "white", backgroundColor: "rgba(0, 0, 0, 0.7)", display: "flex", flexWrap: "nowrap", overflowX: "auto", overflowY: "hidden"}}
                cols={Math.max(imageListItems.length, 1)}
                rowHeight={120}
              >
                {imageListItems.map((item) => (
                  <ImageListItem key={item.index} style={{width: "120px", height: "120px", flex: "0 0 120px"}}>
                    <ButtonBase
                      component="button"
                      type="button"
                      aria-label={`Guess ${item.weaponName}`}
                      disabled={guessedWeapons.includes(item.weaponName) || isGameOver}
                      onClick={() => onWeaponSelect(item.weaponName)}
                      sx={{width: "100%", height: "100%", display: "block", borderRadius: "25px", overflow: "hidden", opacity: guessedWeapons.includes(item.weaponName) ? 0.45 : 1}}
                    >
                      <img src={item.image} alt={item.weaponName} style={{width: "100%", height: "100%", objectFit: "cover"}} />
                    </ButtonBase>
                    <ImageListItemBar sx={{textAlign: "center", fontFamily: "SD", "& .MuiImageListItemBar-subtitle": {fontSize: "85%"}}} subtitle={item.weaponName}/>
                  </ImageListItem>
                ))}
              </ImageList>
            </>

          ) : (

            <ImageList style={{ width: "85%", height: "200px", border: "1px solid", borderColor: "white" }} cols={3} rowHeight={120}>
              <ImageListItem key="Subheader" cols={3} style={{marginBottom: "-25%"}}>
                <ListSubheader component="div" style={{backgroundColor: "gray", display: "flex", alignItems: "center", justifyContent: "space-between", fontWeight: "bolder", color:"black", fontFamily: "SD", fontSize: "115%"}}>
                  <IconButton size="small" aria-label="Previous game" disabled={page === 1} onClick={() => setPage(page - 1)}>
                    <KeyboardArrowLeftIcon />
                  </IconButton>
                  {currentGame}
                  <IconButton size="small" aria-label="Next game" disabled={page === imageLists.length} onClick={() => setPage(page + 1)}>
                    <KeyboardArrowRightIcon />
                  </IconButton>
                </ListSubheader>
              </ImageListItem>
              {imageListItems.map((item) => (
                <ImageListItem key={item.index}>
                  <ButtonBase
                    component="button"
                    type="button"
                    aria-label={`Guess ${item.weaponName}`}
                    disabled={guessedWeapons.includes(item.weaponName) || isGameOver}
                    onClick={() => onWeaponSelect(item.weaponName)}
                    sx={{width: "100%", height: "100%", display: "block", borderRadius: "15px", overflow: "hidden", opacity: guessedWeapons.includes(item.weaponName) ? 0.45 : 1}}
                  >
                    <img src={item.image} alt={item.weaponName} style={{width: "100%", height: "100%", objectFit: "cover"}} />
                  </ButtonBase>
                  <ImageListItemBar style={{textAlign: "center", fontFamily: "SD"}} subtitle={item.weaponName}/>
                </ImageListItem>
              ))}
            </ImageList>

          )}
        </div>
    )
}