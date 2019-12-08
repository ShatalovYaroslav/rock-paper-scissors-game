
function objectifyForm(formArray) {//serialize data function

  var returnArray = {};
  for (var i = 0; i < formArray.length; i++){
    returnArray[formArray[i]['name']] = formArray[i]['value'];
  }
  return returnArray;
}
function submitform(){
    var formElem = document.getElementById("form_id")
    var jsonObject = objectifyForm($(formElem).serializeArray())
    var formData = JSON.stringify(jsonObject);
    console.log("formData: " + formData)

    $.ajax({
      type: "POST",
      url: "/game/playWithPC/",
      data: formData,
        contentType:"application/json; charset=utf-8",
      success: function(){
              console.log("Player's move has been successfully posted");
              alert("The Player's move has been successfully sent");
        }
      }
    );
}