<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar">
    <div class="container">
        <a href="/" class="brand">ETU<span>2818</span></a>
        <div class="nav-links">
            <a href="/" class="nav-item ${pageContext.request.requestURI == '/' ? 'active' : ''}">Dashboard</a>
            <a href="/grades" class="nav-item ${pageContext.request.requestURI.contains('grades') ? 'active' : ''}">Resultats</a>
            <a href="/simulate" class="nav-item ${pageContext.request.requestURI.contains('simulate') ? 'active' : ''}">Simulation</a>
            <a href="/admin/notes" class="nav-item ${pageContext.request.requestURI.contains('notes') ? 'active' : ''}">Notes</a>
            <a href="/admin/candidats" class="nav-item ${pageContext.request.requestURI.contains('candidats') ? 'active' : ''}">Candidats</a>
            <a href="/admin/parametres" class="nav-item ${pageContext.request.requestURI.contains('parametres') ? 'active' : ''}">Configurations</a>
            <a href="/admin/operateurs" class="nav-item ${pageContext.request.requestURI.contains('operateurs') ? 'active' : ''}">Operateurs</a>
        </div>
    </div>
</nav>
