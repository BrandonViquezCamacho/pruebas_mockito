document.getElementById("loginForm").addEventListener("submit", async function(e){
    e.preventDefault();


    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const mensaje = document.getElementById("mensaje");

    const data ={
        email : email,
        password : password
    };

    try {
        const response= await fetch("http://localhost:8080/api/user/login", {
            method: "POST",
            headers: {"Content-Type":"application/json"},
            body: JSON.stringify(data)
        });

        const texto = await response.text();

        if (response.ok){
            mensaje.style.color="green";
            mensaje.textContent = "Acceso permitido";

            setTimeout(()=>{
                window.location.href="dashboard.html";
            }, 1000);
        }else{
            mensaje.style.color="red";
            mensaje.textContent = "texto";
        }

    }catch(error){
            mensaje.style.color="red";
            mensaje.textContent = "Error al conectar servidor";
    }


});