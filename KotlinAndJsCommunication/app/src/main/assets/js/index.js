document.querySelector("#btn_submit").addEventListener("click", function(){
    var data = document.querySelector("#text_area").value;
    Android.submitToActivity(data);
})

document.querySelector("#change_page").addEventListener("click", function(){
    Android.changePage();
})

function onSubmit(data){
    document.querySelector("#text_area").value = data;
}