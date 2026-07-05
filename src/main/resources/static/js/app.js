// Función principal para cambiar la vista (Navegación tipo SPA)
function showView(viewId) {
    // 1. Ocultar todas las secciones
    const views = document.querySelectorAll('.view-section');
    views.forEach(view => {
        view.classList.add('d-none');
    });

    // 2. Mostrar la sección solicitada
    const targetView = document.getElementById('view-' + viewId);
    if (targetView) {
        targetView.classList.remove('d-none');
    }
    
    // 3. Cerrar el menú hamburguesa en móviles si está abierto
    const navbarCollapse = document.getElementById('navbarNav');
    if (navbarCollapse.classList.contains('show')) {
        const bsCollapse = new bootstrap.Collapse(navbarCollapse);
        bsCollapse.hide();
    }
}

// Simular el proceso de Login
function login() {
    // Ocultar botones de visitante
    document.querySelectorAll('.guest-only').forEach(el => el.classList.add('d-none'));
    
    // Mostrar botones de usuario logueado (Dashboard, Perfil, Logout)
    document.querySelectorAll('.logged-in-only').forEach(el => el.classList.remove('d-none'));
    
    // Redirigir al dashboard
    showView('dashboard');
}

// Simular el proceso de Logout
function logout() {
    // Mostrar botones de visitante
    document.querySelectorAll('.guest-only').forEach(el => el.classList.remove('d-none'));
    
    // Ocultar botones de usuario logueado
    document.querySelectorAll('.logged-in-only').forEach(el => el.classList.add('d-none'));
    
    // Volver al inicio
    showView('home');
}

// Lógica simulada para el juego
function nextQuestion() {
    // Aquí puedes añadir la animación o lógica para recargar el card con la siguiente pregunta
    const btn = event.target;
    const originalText = btn.innerHTML;
    
    btn.innerHTML = "Cargando...";
    btn.disabled = true;

    // Simulamos un retraso de red
    setTimeout(() => {
        btn.innerHTML = originalText;
        btn.disabled = false;
        // Alerta demostrativa
        alert("En producción, esto cargará la siguiente pregunta.");
    }, 600);
}