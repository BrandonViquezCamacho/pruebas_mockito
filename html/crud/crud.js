const API = "http://localhost:8080/api/user";

const form = document.getElementById("userForm");
const table = document.getElementById("userTable");
const mensaje = document.getElementById("mensaje");

let editando = false;
let currentId = null;

// =====================================
// CARGAR USUARIOS
// =====================================
async function cargarUsuarios() {
  try {
    const response = await fetch(API);
    const users = await response.json();

    table.innerHTML = "";

    users.forEach((user) => {
      table.innerHTML += `

                <tr>

                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.rol || ""}</td>

                    <td>
                        <button onclick="editarUsuario(${user.id})">
                            Editar
                        </button>
                        <button onclick="eliminarUsuario(${user.id})">
                            Eliminar
                        </button>
                    </td>
                </tr>
            `;
    });
  } catch (error) {
    mensaje.style.color = "red";
    mensaje.textContent = "Error cargando usuarios";
  }
}

// =====================================
// GUARDAR / EDITAR
// =====================================
form.addEventListener("submit", async function (e) {
  e.preventDefault();

  const data = {
    id: document.getElementById("id").value,
    name: document.getElementById("name").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value,
    rol: document.getElementById("rol").value,
  };

  try {
    let response;

    // =====================================
    // CREAR
    // =====================================
    if (!editando) {
      response = await fetch(`${API}/save`, {
        method: "POST",

        headers: {
          "Content-Type": "application/json",
        },

        body: JSON.stringify(data),
      });
    } else {
      // =====================================
      // EDITAR
      // =====================================
      response = await fetch(`${API}/${currentId}`, {
        method: "PUT",

        headers: {
          "Content-Type": "application/json",
        },

        body: JSON.stringify(data),
      });

      editando = false;
      currentId = null;
    }

    const texto = await response.text();

    if (response.ok) {
      mensaje.style.color = "green";
      mensaje.textContent = "Operación realizada correctamente";

      form.reset();

      cargarUsuarios();
    } else {
      mensaje.style.color = "red";
      mensaje.textContent = texto;
    }
  } catch (error) {
    mensaje.style.color = "red";
    mensaje.textContent = "Error conectando servidor";
  }
});

// =====================================
// EDITAR
// =====================================
async function editarUsuario(id) {
  try {
    const response = await fetch(`${API}/${id}`);

    const user = await response.json();

    document.getElementById("id").value = user.id;
    document.getElementById("name").value = user.name;
    document.getElementById("email").value = user.email;
    document.getElementById("password").value = user.password || "";
    document.getElementById("rol").value = user.rol || "USER";

    editando = true;
    currentId = id;

    mensaje.style.color = "blue";
    mensaje.textContent = "Modo edición";
  } catch (error) {
    mensaje.style.color = "red";
    mensaje.textContent = "Error obteniendo usuario";
  }
}

// =====================================
// ELIMINAR
// =====================================
async function eliminarUsuario(id) {
  const confirmar = confirm("¿Desea eliminar este usuario?");

  if (!confirmar) {
    return;
  }

  try {
    const response = await fetch(`${API}/${id}`, {
      method: "DELETE",
    });

    if (response.ok) {
      mensaje.style.color = "green";
      mensaje.textContent = "Usuario eliminado";

      cargarUsuarios();
    } else {
      mensaje.style.color = "red";
      mensaje.textContent = "No se pudo eliminar";
    }
  } catch (error) {
    mensaje.style.color = "red";
    mensaje.textContent = "Error conectando servidor";
  }
}

// =====================================
// INICIAR
// =====================================
cargarUsuarios();
