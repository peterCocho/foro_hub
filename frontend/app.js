// app.js

let token = ""; // Aquí guardaremos el token una vez logueado

async function login() {
  const email = document.getElementById("loginEmail").value;
  const password = document.getElementById("loginPassword").value;

  try {
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        login: email,
        password: password
      })
    });

    if (response.ok) {
      const data = await response.json();
      token = data.token;
      alert("✅ Login exitoso");
    } else {
      alert("❌ Login fallido. Verifica tus credenciales.");
    }
  } catch (error) {
    alert("🚨 Error en la conexión con el backend");
    console.error(error);
  }
}

async function crearTopico() {
  const titulo = document.getElementById("titulo").value;
  const mensaje = document.getElementById("mensaje").value;
  const curso = document.getElementById("curso").value;
  const usuarioId = document.getElementById("usuarioId").value;

  try {
    const response = await fetch("http://localhost:8080/topicos", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      },
      body: JSON.stringify({
        titulo: titulo,
        mensaje: mensaje,
        curso: curso,
        autorId: usuarioId
      })
    });

    if (response.status === 201) {
      const data = await response.json();
      alert("✅ Tópico creado con ID: " + data.id);
    } else if (response.status === 403) {
      alert("🔒 No estás autenticado. Inicia sesión primero.");
    } else {
      alert("⚠️ Error al crear el tópico.");
    }
  } catch (error) {
    alert("🚨 Falló la conexión al backend.");
    console.error(error);
  }
}
