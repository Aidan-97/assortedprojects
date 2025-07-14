import { Grid, Paper } from "@mui/material";
import styled from "styled-components";
import { isBrowser } from "react-device-detect";
import ReactCardFlip from "react-card-flip";

export default function Guess({guessStats, targetStats, isFlipped}){

    const PaperStyle = styled.div`
        .paper{
            height: 40px;
            width: 100px;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    `

    const MobilePaperStyle = styled.div`
        .m-paper{
            height: 40px;
            width: 90px;
            margin-bottom: 1px;
            display: flex;
            align-items: center;
            justify-content: center;
        }
    `

    const guessStatsArray = [guessStats.console, guessStats.game, guessStats.planet, guessStats.range, guessStats.rof, guessStats.cost];
    const targetStatsArray = [targetStats.console, targetStats.game, targetStats.planet, targetStats.range, targetStats.rof, targetStats.cost];

    const mobilePanelArray = ["Console", "Game", "Planet/Level", "Range", "Rate of Fire", "Cost (Bolts)"];

    return (

        <>{ isBrowser ?

            <Grid container xs={12} spacing={2} style={{display: "flex", alignItems: "center", justifyContent: "center"}}>
                {[0, 1, 2, 3, 4, 5].map((value) => (
                    <Grid item>
                        <ReactCardFlip flipDirection='horizontal' isFlipped={isFlipped}>
                            <PaperStyle>
                                <Paper className="paper" style={{backgroundColor: "lightgray"}}><div style={{textAlign: "center", display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>___</div></Paper>
                            </PaperStyle>
                            <PaperStyle>
                                {guessStatsArray[value] === targetStatsArray[value] ?
                                    <Paper className="paper" style={{backgroundColor: "green"}}><div style={{textAlign: "center", display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>{guessStatsArray[value]}</div></Paper> 
                                    :
                                    <Paper className="paper" style={{backgroundColor: "red"}}><div style={{textAlign: "center", display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>{guessStatsArray[value]}</div></Paper>
                                }
                            </PaperStyle>
                        </ReactCardFlip>
                    </Grid>
                ))}
            </Grid> :

            <Grid container xs={12} spacing={1} style={{display: "flex", alignItems: "center", justifyContent: "center"}}>
            {[0, 1, 2, 3, 4, 5].map((value) => (
                <Grid item>
                    <ReactCardFlip flipDirection='horizontal' isFlipped={isFlipped}>
                        <MobilePaperStyle>
                            <Paper className="m-paper" style={{backgroundColor: "lightgray"}}><div style={{textAlign: "center", display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>{mobilePanelArray[value]}</div></Paper>
                        </MobilePaperStyle>
                        <MobilePaperStyle>
                            {guessStatsArray[value] === targetStatsArray[value] ?
                                <Paper className="m-paper" style={{backgroundColor: "green"}}><div style={{textAlign: "center", display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>{guessStatsArray[value]}</div></Paper> 
                                :
                                <Paper className="m-paper" style={{backgroundColor: "red"}}><div style={{textAlign: "center", display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>{guessStatsArray[value]}</div></Paper>
                            }
                        </MobilePaperStyle>
                    </ReactCardFlip>
                </Grid>
            ))}
            </Grid>

        }</>

    )

}