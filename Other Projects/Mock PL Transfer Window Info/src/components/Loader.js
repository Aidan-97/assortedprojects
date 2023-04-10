import spinner from './spinner.gif'

const Loader = () => {
    return (
        <div style={{display: "flex", alignItems: "center", justifyContent: "center"}}>
            <img src={spinner} alt="loading" />
            <h5>Fetching Data</h5>
        </div>
    )
}

export default Loader