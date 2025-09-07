import {Chart} from "react-google-charts"
import { BrowserView, MobileView } from 'react-device-detect';

export default function CoasterDesignBarChart({chartData}){
    return (
        <>
            <BrowserView>
                <Chart 
                    style={{width: "50vw", paddingLeft: "22.5vw", paddingTop: 2.5, height: "45vh"}}
                    chartType='ColumnChart'
                    data={chartData}
                    options={{
                        legend: 'none',
                        chartArea: {width: '70%'},
                        backgroundColor: "black",
                        hAxis: {textStyle:{color: "white"}},
                        vAxis: {textStyle:{color: "white"}},
                    }}
                />    
            </BrowserView>

            <MobileView>
                <Chart 
                    style={{height: "40vh"}}
                    chartType='ColumnChart'
                    data={chartData}
                    options={{
                        legend: 'none',
                        chartArea: {width: '70%'},
                        backgroundColor: "black",
                        hAxis: {textStyle:{color: "white"}},
                        vAxis: {textStyle:{color: "white"}},
                    }}
                />
            </MobileView>
        </>
    )
}