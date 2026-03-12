<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GradeV2 - Dashboard</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <jsp:include page="common/navbar.jsp" />

    <div class="container">
        <div class="glass-card">
            <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem;">
                <h2>Liste des Candidats</h2>
                <a href="/admin/candidats" class="btn btn-outline" style="color: var(--accent);">Gerer les Candidats</a>
            </div>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Matricule</th>
                            <th>Nom & Prenom</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${candidats}" var="c">
                            <tr>
                                <td><span class="badge blue">${c.matricule}</span></td>
                                <td style="font-weight: 600;">${c.nom} ${c.prenom}</td>
                                <td>
                                    <a href="/candidat/${c.id}" class="btn btn-primary" style="padding: 0.5rem 1rem; font-size: 0.8rem;">Voir Details & Notes</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
