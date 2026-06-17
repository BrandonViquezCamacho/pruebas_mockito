const API_RESERVATION = "http://localhost:8080/api/reservation";
const API_USER = "http://localhost:8080/api/user";
const API_SPACE = "http://localhost:8080/api/spaces";

const form = document.getElementById("reservationForm");
const table = document.getElementById("reservationTable");
const mensaje = document.getElementById("mensaje");

let editando = false;
let currentId = null;

window.onload = async () => {
    await cargarUsuarios();
    await cargarEspacios();
    await cargarReservaciones();
};

async function cargarUsuarios() {

    const response = await fetch(API_USER);
    const users = await response.json();

    const select = document.getElementById("user");

    select.innerHTML = "";

    users.forEach(user => {

        select.innerHTML += `
            <option value="${user.id}">
                ${user.name}
            </option>
        `;
    });
}

async function cargarEspacios() {

    const response = await fetch(API_SPACE);
    const spaces = await response.json();

    const select = document.getElementById("space");

    select.innerHTML = "";

    spaces.forEach(space => {

        select.innerHTML += `
            <option value="${space.id}">
                ${space.name}
            </option>
        `;
    });
}

async function cargarReservaciones() {

    const response = await fetch(API_RESERVATION);

    const reservations = await response.json();

    table.innerHTML = "";

    reservations.forEach(reservation => {

        table.innerHTML += `
            <tr>

                <td>${reservation.id}</td>

                <td>${reservation.userName}</td>

                <td>${reservation.creativeSpaceName}</td>

                <td>${reservation.dateReserved}</td>

                <td>${reservation.status}</td>

                <td>

                    <button onclick="editarReservation(${reservation.id})">
                        Editar
                    </button>

                    <button onclick="eliminarReservation(${reservation.id})">
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

        creativeSpace: {
            id: Number(
                document.getElementById("space").value
            )
        },

        user: {
            id: Number(
                document.getElementById("user").value
            )
        },

        dateReserved:
            document.getElementById("dateReserved").value,

        status:
            document.getElementById("status").value
    };

    let response;

    if (!editando) {

        response = await fetch(`${API_RESERVATION}/save`, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(data)
        });

    } else {

        response = await fetch(`${API_RESERVATION}/${currentId}`, {

            method: "PUT",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(data)
        });

        editando = false;
        currentId = null;
    }

    if (response.ok) {

        mensaje.textContent =
            "Reservación guardada correctamente";

        form.reset();

        cargarReservaciones();

    } else {

        mensaje.textContent =
            "Error al guardar";
    }

});

async function editarReservation(id) {

    const response =
        await fetch(`${API_RESERVATION}/${id}`);

    const reservation =
        await response.json();

    document.getElementById("user").value =
        reservation.userId;

    document.getElementById("space").value =
        reservation.creativeSpaceId;

    document.getElementById("dateReserved").value =
        reservation.dateReserved;

    document.getElementById("status").value =
        reservation.status;

    editando = true;
    currentId = id;
}

async function eliminarReservation(id) {

    if (!confirm("¿Eliminar reservación?")) {
        return;
    }

    await fetch(`${API_RESERVATION}/${id}`, {
        method: "DELETE"
    });

    cargarReservaciones();
}