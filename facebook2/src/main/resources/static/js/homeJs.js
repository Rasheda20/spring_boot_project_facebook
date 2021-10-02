
var settingsmenu = document.querySelector(".settings_menu")

var darkbtn=document.getElementById("dark-btn");
function settingsMenuToggle(){

    settingsmenu.classList.toggle("settings_menu-height")


}

darkbtn.onclick = function(){
    darkbtn.classList.toggle("dark-btn-on");
    document.body.classList.toggle("dark-theme");


}

// if()

// localStorage.setItem("theme", "dark");
// localStorage.getItem("theme");




