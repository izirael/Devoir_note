<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <nav class="navbar">
        <div class="container">
            <a href="/" class="brand">ETU<span>2818</span></a>
            <div class="nav-links">
                <a href="/" class="nav-item ${pageContext.request.requestURI == '/' ? 'active' : ''}">Dashboard</a>

                <a href="/simulate"
                    class="nav-item ${pageContext.request.requestURI.contains('simulate') ? 'active' : ''}">Simulation</a>
                <a href="/admin/notes"
                    class="nav-item ${pageContext.request.requestURI.contains('notes') ? 'active' : ''}">Notes</a>
                <a href="/admin/candidats"
                    class="nav-item ${pageContext.request.requestURI.contains('candidats') ? 'active' : ''}">Candidats</a>
                <div class="dropdown">
                    <a href="#"
                        class="nav-item ${pageContext.request.requestURI.contains('admin') && !pageContext.request.requestURI.contains('notes') && !pageContext.request.requestURI.contains('candidats') ? 'active' : ''}">Paramètres
                        ▾</a>
                    <div class="dropdown-content">
                        <a href="/admin/parametres"
                            class="nav-item ${pageContext.request.requestURI.contains('parametres') ? 'active' : ''}">Configurations</a>
                        <a href="/admin/operateurs"
                            class="nav-item ${pageContext.request.requestURI.contains('operateurs') ? 'active' : ''}">Opérateurs</a>
                        <a href="/admin/correcteurs"
                            class="nav-item ${pageContext.request.requestURI.contains('correcteurs') ? 'active' : ''}">Correcteurs</a>
                        <a href="/admin/matieres"
                            class="nav-item ${pageContext.request.requestURI.contains('matieres') ? 'active' : ''}">Matières</a>
                        <a href="/admin/resolutions"
                            class="nav-item ${pageContext.request.requestURI.contains('resolutions') ? 'active' : ''}">Résolutions</a>
                    </div>
                </div>
            </div>
        </div>
    </nav>