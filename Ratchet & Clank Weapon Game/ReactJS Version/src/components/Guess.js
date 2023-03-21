import { Grid, Paper } from "@mui/material";
import styled from "styled-components";

export default function Guess({guessStats, targetStats}){

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
    const guessStatsArray = [guessStats.console, guessStats.game, guessStats.planet, guessStats.range, guessStats.rof, guessStats.cost];
    const targetStatsArray = [targetStats.console, targetStats.game, targetStats.planet, targetStats.range, targetStats.rof, targetStats.cost];

    return (

        <Grid container xs={12} spacing={2} style={{display: "flex", alignItems: "center", justifyContent: "center"}}>
            {[0, 1, 2, 3, 4, 5].map((value) => (
                <Grid item>
                    { guessStatsArray[value] === targetStatsArray[value] ?
                    <PaperStyle>
                        <Paper className="paper" style={{backgroundColor: "green"}}><div style={{display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>{guessStatsArray[value]}</div></Paper>
                    </PaperStyle>
                    :
                    <> {guessStatsArray[value] !== "..." ?
                        <PaperStyle>
                            <Paper className="paper" style={{backgroundColor: "red"}}><div style={{display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>{guessStatsArray[value]}</div></Paper>
                        </PaperStyle>
                        :
                        <PaperStyle>
                            <Paper className="paper" style={{backgroundColor: "lightgray"}}><div style={{display: "flex", alignItems: "center", justifyContent: "center", fontSize: "80%", fontWeight: 600}}>{guessStatsArray[value]}</div></Paper>
                        </PaperStyle>}
                    </>
                    }
                </Grid>
            ))}
        </Grid>

    )

}