/*jslint node: true, browser: true */
"use strict";

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// for canvas
var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");

// initial position of ball
var xPos = canvas.width/2;
var yPos = canvas.height-30;

// ball speed
var xMove = 2;
var yMove = -2;

// ball radius
var ballRad = 10;

// for drawing paddle
var paddleHeight = 10;
var paddleWidth = 70;
var paddlePos = (canvas.width - paddleWidth) / 2;

// gamma value of device, for moving paddle side-to-side
// var tilt = 0;

var rightPressed = false;
var leftPressed = false;

// count how many blocks have been hit
var successfulHits = 0;

// for drawing grid of blocks
var rows = 4;
var columns = 7;
var blockWidth = 35;
var blockHeight = 20;
var brickPad = 10;
var topPad = 50;
var leftPad = 20;

document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);

// initialise block grid
var blocks = [];
for(var col=0; col < columns; col++){
    blocks[col] = [];
    for(var row=0; row < rows; row++){
        blocks[col][row] = { x: 0, y: 0, hit: false };
    }
}

//if(window.DeviceOrientationEvent){
//    window.addEventListener('deviceorientation', function(event){
//        tilt = Math.round(event.gamma);
//        
//        // document.getElementById("acc3").innerHTML = "gamma="+tilt;
//    });
//}

function addPaddle(){
    context.beginPath();
    context.rect(paddlePos, canvas.height-paddleHeight, paddleWidth, paddleHeight);
    context.fillStyle = "white";
    context.fill();
    context.closePath();
}

function addBall(){
    context.beginPath();
    context.arc(xPos, yPos, ballRad, 0, Math.PI*2);
    context.fillStyle = "blue";
    context.fillStroke = "blue";
    context.Stroke = "10";
    context.fill();
    context.closePath();
}

function addBlocks(){
    for(var col=0; col < columns; col++){
        for(var row=0; row < rows; row++){
            if(blocks[col][row].hit === false){
                var blockX = (col*(blockWidth + brickPad)) + leftPad;
                var blockY = (row*(blockHeight + brickPad)) + topPad;
                blocks[col][row].x = blockX;
                blocks[col][row].y = blockY;
                context.beginPath();
                context.rect(blockX, blockY, blockWidth, blockHeight);
            
                if (row === 0){
                    context.fillStyle = "red";
                }
                else if (row === 1){
                    context.fillStyle = "yellow";
                }
                else if (row === 2){
                    context.fillStyle = "green";
                }
                else if (row === 3){
                    context.fillStyle = "purple";
                }
            
                context.fill();
                context.closePath();
            }
        }
    }
}

function blockCollisions(){
    for(var col=0; col < columns; col++){
        for(var row=0; row < rows; row++){
            var block = blocks[col][row];
            if(block.hit === false){
                if(xPos > block.x && xPos < block.x + blockWidth && yPos > block.y && yPos < block.y + blockHeight){
                    yMove = -yMove;
                    block.hit = true;
                    successfulHits++;
                    if(successfulHits === rows * columns){
                        alert("You Win! - Score: " + successfulHits);
                        document.location.reload();
                    }
                }
            }
        }
    }
}

function keyDownHandler(e) {
    if(e.key === "Right" || e.key === "ArrowRight") {
        rightPressed = true;
    }
    else if(e.key === "Left" || e.key === "ArrowLeft") {
        leftPressed = true;
    }
}

function keyUpHandler(e) {
    if(e.key === "Right" || e.key === "ArrowRight") {
        rightPressed = false;
    }
    else if(e.key === "Left" || e.key === "ArrowLeft") {
        leftPressed = false;
    }
}

function drawAll(){
    context.clearRect(0, 0, canvas.width, canvas.height);
    addBlocks();
    addBall();
    addPaddle();
    blockCollisions();
    
    //for wall collisions
    if(xPos + xMove > canvas.width - ballRad || xPos + xMove < ballRad){
        xMove = -xMove;
    }
    
    // for paddle collisions   
    if(yPos + yMove < ballRad){
        yMove = -yMove;
    }
    else if(yPos + yMove > canvas.height - ballRad){
        if(xPos > paddlePos && xPos < paddlePos + paddleWidth){
            yMove = -yMove;
        }
        else{
            alert("Game Over - Score: "+successfulHits);
            document.location.reload();
            clearInterval(interval);
        }
    }
    
    //for moving paddle upon tilting device
//    if(tilt > 10 && paddlePos < canvas.width - paddleWidth){
//        paddlePos += 5;
//    }
//    else if(tilt < -10 && paddlePos > 0){
//        paddlePos -= 5;
//    }

    //for moving paddle with right/left keys
    if(rightPressed) {
        paddlePos += 7;
        if (paddlePos + paddleWidth > canvas.width){
            paddlePos = canvas.width - paddleWidth;
        }
    }
    else if(leftPressed) {
        paddlePos -= 7;
        if (paddlePos < 0){
            paddlePos = 0;
        }
    }
    
    xPos += xMove;
    yPos += yMove;
}

var interval = setInterval(drawAll, 10);
