const hands = ["rock", "paper", "scissor"];
const rock = document.getElementById("rock");
const paper = document.getElementById("paper");
const scissor = document.getElementById("scissor");
const comp_hand = document.getElementById("comp-hand");
const user_score = document.getElementById("user-score");
const comp_score = document.getElementById("comp-score");
let resultMsg = document.getElementById("result");
let userScore = 0;
let compScore = 0;

let compDraw = () => {
  let num_hands = 3; // three possible hands
  return Math.floor(Math.random() * num_hands);
};

let play = (e) => {
  let result = "";
  let comp_hand = compDraw();
  let user_hand = hands.indexOf(e.target.id);

  compDisplay(comp_hand); // show computer's hand in UI

  if (user_hand === comp_hand) {
    result = "tie";
  }

  switch (user_hand) {
    case 0: // user_hand = rock
      if (comp_hand === hands.indexOf("paper")) {
        result = "lose";
      }
      break;
    case 1: // user_hand = paper
      if (comp_hand === hands.indexOf("scissor")) {
        result = "lose";
      }
      break;
    case 2: // user_hand = scissor
      if (comp_hand === hands.indexOf("rock")) {
        result = "lose";
      }
      break;
  }

  if (result === "") {
    result = "win"; // tie and lose scenarios eliminated
  }

  updateScore(result);
};

let compDisplay = (hand) => {
  switch (hand) {
    case 0:
      comp_hand.innerHTML =
        "<img src='images/rock.png' class='img-fluid' alt='rock'>";
      break;
    case 1:
      comp_hand.innerHTML =
        "<img src='images/paper.png' class='img-fluid' alt='paper'>";
      break;
    case 2:
      comp_hand.innerHTML =
        "<img src='images/scissors.png' class='img-fluid' alt='scissor'>";
      break;
  }
};

let updateScore = (result) => {
  if (result === "win") {
    userScore++;
    user_score.textContent = userScore.toString();
    resultMsg.innerHTML = "<h3>You Win!</h3>";
  } else if (result === "lose") {
    compScore++;
    comp_score.textContent = compScore.toString();
    resultMsg.innerHTML = "<h3>Computer Wins.</h3>";
  } else {
    resultMsg.innerHTML = "<h3>Same hands, it's a tie.</h3>";
  }
};

rock.addEventListener("click", play, false);
paper.addEventListener("click", play, false);
scissor.addEventListener("click", play, false);
