import React, { useRef, useState } from "react";
import './ImageGeneratorStyle.css'
import defaultImage from './image-placeholder-2.jpg'

const ImageGenerator = () => {

    const [imageURL, setImageURL] = useState("/");
    let inputRef = useRef(null);

    const [loading, setLoading] = useState(false);

    const imageGenerator = async () => {
        if (inputRef.current.value === "") {
            return 0;
        }
        setLoading(true);
        const response = await fetch(
            "https://api.openai.com/v1/images/generations",
            {
                method: "POST",
                headers:{
                    "Content-Type":"application/json",
                    Authorization:
                    "Bearer [API KEY GOES HERE]",
                    "User-Agent":"Chrome"
                },
                body: JSON.stringify({
                    prompt: `${inputRef.current.value}`,
                    n: 1,
                    size: "512x512",
                }),
            }
        );
        let data = await response.json();
        let dataArray = data.data;
        setImageURL(dataArray[0].url);
        setLoading(false);
    }

    return (
        <div className="ai-image-generator">
            <div className="header">OpenAI <span>Image Generator</span></div>
            <div className="img-loading">
                <div className="image"><img src={imageURL==="/" ? defaultImage : imageURL} alt="" /></div>
                <div className="loading">
                    <div className={loading ? "loading-bar-full" : "loading-bar"}></div>
                    <div className={loading ? "loading-text" : "display-none"}>Loading...</div>
                </div>
            </div>
            <div className="search-box">
                <input type="text" ref={inputRef} className="search-input" placeholder="Enter prompt here..." />
                <div className="generate-btn" onClick={() => {imageGenerator()}}>Generate</div>
            </div>
        </div>
    )
}

export default ImageGenerator