import { ImageList, ImageListItem, ListSubheader, ImageListItemBar } from "@mui/material"
import { isBrowser } from "react-device-detect"

export default function ImageListDisplay({weaponNames, weaponImages}){
    return (
        <>{ isBrowser ?

            <ImageList style={{ width: "680px", height: "300px", border: "1px solid", borderColor: "white" }} cols={3} rowHeight={240}>
              <ImageListItem key="Subheader" cols={3} style={{marginBottom: "-200px"}}>
                <ListSubheader component="div" style={{backgroundColor: "gray", textAlign: "center", fontWeight: "bolder", color:"black", fontFamily: "SD", fontSize: "125%"}}>Weapons</ListSubheader>
              </ImageListItem>
              {weaponImages.slice(1).map((item) => (
                <ImageListItem>
                  <img 
                    src={`${item}`}
                    alt=""
                    style={{borderRadius: "25px"}}
                  />
                  <ImageListItemBar sx={{textAlign: "center", fontFamily: "SD", "& .MuiImageListItemBar-subtitle": {fontSize: "115%"}}} subtitle={weaponNames[weaponImages.indexOf(item)]}/>
                </ImageListItem>
              ))}
            </ImageList>
            
            :

            <ImageList style={{ width: "85%", height: "200px", border: "1px solid", borderColor: "white" }} cols={3} rowHeight={120}>
              <ImageListItem key="Subheader" cols={3} style={{marginBottom: "-25%"}}>
                <ListSubheader component="div" style={{backgroundColor: "gray", textAlign: "center", fontWeight: "bolder", color:"black", fontFamily: "SD", fontSize: "115%"}}>Weapons</ListSubheader>
              </ImageListItem>
              {weaponImages.slice(1).map((item) => (
                <ImageListItem>
                  <img 
                    src={`${item}`}
                    alt=""
                    style={{borderRadius: "15px"}}
                  />
                  <ImageListItemBar style={{textAlign: "center", fontFamily: "SD"}} subtitle={weaponNames[weaponImages.indexOf(item)]}/>
                </ImageListItem>
              ))}
            </ImageList>

          }</>
    )
}
