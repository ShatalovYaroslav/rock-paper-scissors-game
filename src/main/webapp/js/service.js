


function objectifyForm(formArray) {//serialize data function

  var returnArray = {};
  for (var i = 0; i < formArray.length; i++){
    returnArray[formArray[i]['name']] = formArray[i]['value'];
  }
  return returnArray;
}

function getResultForPlayer (playerId, resultData) {
    for (var i = 0; i < resultData.length; i++){
      if (resultData[i].player_id == playerId){
        return resultData[i].game_result
      }
    }
}

function replaceContentInContainer(id, content) {
    var container = document.getElementById(id);
    container.innerHTML = content;
}

function renderResult (playerId, resultData) {
    console.log("Result data: " + JSON.stringify(resultData));
    var opponent;
      if (resultData[0].playerId == playerId) {
        opponent = resultData[1];
      } else {
        opponent = resultData[0];
      }
    replaceContentInContainer("Result", "The result for you: " + getResultForPlayer(playerId, resultData));
    replaceContentInContainer("OpponentResult", "The move of your opponent: " + opponent.move);
}

function submitform(){
    var playerId = document.getElementById("player_id").value;
    if (!playerId){
        alert("The Player ID should not be empty");
        return;
    }
    var formElem = document.getElementById("form_id")
    var jsonObject = objectifyForm($(formElem).serializeArray())
    var formData = JSON.stringify(jsonObject);
    console.log("formData: " + formData)

    $.ajax({
      type: "POST",
      url: "/game/playWithPC/",
      data: formData,
      contentType:"application/json; charset=utf-8",
      success: function(data){
      console.log(data)
            console.log("Player's move has been successfully posted");
                    alert("The Player's move has been successfully sent");
             renderResult(playerId, data);
        },
        error: function(xhr, type, exception) {
          // if ajax fails display error alert
          alert("ajax error response type " + type);
        }
    });
}