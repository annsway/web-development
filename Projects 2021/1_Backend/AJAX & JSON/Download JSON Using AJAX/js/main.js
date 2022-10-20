var btn = document.getElementById("btn");
var animalContainer = document.getElementById("animal-info");
var pageCounter = 1;

btn.addEventListener("click", function () {
  var ourRequest = new XMLHttpRequest();
  ourRequest.open(
    "GET",
    "https://learnwebcode.github.io/json-example/animals-" +
      pageCounter +
      ".json"
  );
  ourRequest.onload = function () {
    if (ourRequest.status >= 200 && ourRequest.status < 400) {
      // error handling #2
      // AJAX: tells the Browser that this is JSON format and we want to retrieve data element at index 0
      var ourData = JSON.parse(ourRequest.responseText);
      // console.log(ourData[0]);
      renderHTML(ourData);
    } else {
      console.log(
        "We connected to the server, but there's an error returned. "
      );
    }
  };
  ourRequest.onerror = function () {
    console.log("Server Connection Error. ");
  };
  ourRequest.send();
  pageCounter++;
  if (pageCounter > 3) {
    btn.hidden = true;
  }
});

// create and add HTML to the page
// add to the empty <div>
function renderHTML(data) {
  var htmlString = "";

  for (i = 0; i < data.length; i++) {
    htmlString +=
      "<p>" + data[i].name + " is a " + data[i].species + " that likes the ";
    for (j = 0; j < data[i].foods.likes.length; j++) {
      if (j == 0) {
        htmlString += data[i].foods.likes[j];
      } else {
        htmlString += " and " + data[i].foods.likes[j];
      }
    }
    htmlString += ", and dislikes ";

    for (j = 0; j < data[i].foods.dislikes.length; j++) {
      if (j == 0) {
        htmlString += data[i].foods.dislikes[j];
      } else {
        htmlString += " and " + data[i].foods.dislikes[j];
      }
    }

    htmlString += ". </p>";
  }
  // loop through the JSON data
  animalContainer.insertAdjacentHTML("beforeend", htmlString);
}
