function getGithubInfo(user) {
  //1. Create an instance of XMLHttpRequest class and send a GET request using it.
  // The function should finally return the object(it now contains the response!)

  const url = `https://api.github.com/users/${user}`;
  const xmlhttp = new XMLHttpRequest();
  xmlhttp.open("GET", url, false);
  xmlhttp.send();
  return xmlhttp;
}

function showUser(user) {
  //2. set the contents of the h2 and the two div elements in the div '#profile' with the user content
  $("#profile h2").html(
    `Github profile information for <span class="title">${user.login}</span>`
  );

  // Display user avatar
  $(".avatar").html("<img src='' alt='avatar image' id='avatar' />");
  $("#avatar").attr("src", `${user.avatar_url}`);

  // Display user information
  $("#userInfo").empty();
  $(".information").css("border", "3px solid #000");
  $("#userInfo").append(
    `<li><span class="title">User Name:</span> ${user.name}</li>`
  );
  $("#userInfo").append(
    `<li><span class="title">Location:</span> ${user.location}</li>`
  );
  $("#userInfo").append(
    `<li><span class="title">Company:</span> ${user.company}</li>`
  );
  $("#userInfo").append(
    `<li><span class="title">Biography:</span> ${user.bio}</li>`
  );
  $("#userInfo").append(
    `<li><span class="title">Email:</span> ${user.email}</li>`
  );
  $("#userInfo").append(
    `<li><span class="title">Following:</span> ${user.following}</li>`
  );
  $("#userInfo").append(
    `<li><span class="title">Followers:</span> ${user.followers}</li>`
  );
  $("#userInfo").append(`<li><a href=${user.html_url}>Go to profile</a></li>`);
}

function noSuchUser(username) {
  //3. set the elements such that a suitable message is displayed
  $("#userInfo").empty();
  $(".avatar").empty();
  $(".information").css("border", "none");
  $("#profile h2").html(
    `Github profile information for <span class="title">${username}</span> does not exist.`
  );
}

$(document).ready(function () {
  $(document).on("keypress", "#username", function (e) {
    //check if the enter(i.e return) key is pressed
    if (e.which == 13) {
      //get what the user enters
      username = $(this).val();
      //reset the text typed in the input
      $(this).val("");
      //get the user's information and store the respsonse
      response = getGithubInfo(username);
      //if the response is successful show the user's details
      if (response.status == 200) {
        showUser(JSON.parse(response.responseText));
        //else display suitable message
      } else {
        noSuchUser(username);
      }
    }
  });
});
