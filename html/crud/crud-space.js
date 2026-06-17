const API = "http://localhost:8080/api/spaces";

const form = document.getElementById("spaceForm");
const table = document.getElementById("spaceTable");
const mensaje = document.getElementById("mensaje");

let editando = false;
let currentId = null;

async function cargarEspacios() {

    const response = await fetch(API);
    const data = await response.json();

    table.innerHTML = "";

    data.forEach(space => {

        table.innerHTML += `
            <tr>
                <td>${space.id}</td>
                <td>${space.name}</td>
                <td>${space.location}</td>
                <td>${space.type}</td>
                <td>${space.price}</td>

                <td>
                    <button onclick="editarEspacio(${space.id})">
                        Editar
                    </button>

                    <button onclick="eliminarEspacio(${space.id})">
                        Eliminar
                    </button>
                </td>
            </tr>
        `;
    });
}

form.addEventListener("submit", async (e) => {

    e.preventDefault();

    const data = {
        id: document.getElementById("id").value,
        name: document.getElementById("name").value,
        location: document.getElementById("location").value,
        type: document.getElementById("type").value,
        price: document.getElementById("price").value
    };

    let response;

    if (!editando) {

        response = await fetch(`${API}/save`, {
            method: "POST",
            headers: {
                "Content-Type":"application/json"
            },
            body: JSON.stringify(data)
        });

    } else {

        response = await fetch(`${API}/${currentId}`,{
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify(data)
        });

        editando = false;
        currentId = null;
    }

    if(response.ok){

        mensaje.textContent = "Guardado correctamente";
        form.reset();
        cargarEspacios();

    }else{

        mensaje.textContent = "Error";
    }

});

async function editarEspacio(id){

    const response = await fetch(`${API}/${id}`);
    const space = await response.json();

    document.getElementById("id").value = space.id;
    document.getElementById("name").value = space.name;
    document.getElementById("location").value = space.location;
    document.getElementById("type").value = space.type;
    document.getElementById("price").value = space.price;

    editando = true;
    currentId = id;
}

async function eliminarEspacio(id){

    if(!confirm("Eliminar espacio?")){
        return;
    }

    await fetch(`${API}/${id}`,{
        method:"DELETE"
    });

    cargarEspacios();
}

cargarEspacios();