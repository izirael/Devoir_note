<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GradeV2 - Global Overview</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <jsp:include page="common/navbar.jsp" />

    <div class="container">
        <div class="glass-card">
            <h2>Vue d'ensemble des Resultats</h2>
            <div class="table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Candidat</th>
                            <c:forEach items="${matieres}" var="m">
                                <th style="text-align: center;">${m.nom}<br><small style="font-weight: 400;">(&times;${m.coefficient})</small></th>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${candidats}" var="c">
                            <tr>
                                <td>
                                    <div style="font-weight: 700;">${c.nom} ${c.prenom}</div>
                                    <small style="color: var(--text-secondary);">${c.matricule}</small>
                                </td>
                                <c:forEach items="${matieres}" var="m">
                                    <td style="text-align: center;">
                                        <c:set var="g" value="${gradeMap[c.id][m]}" />
                                        <div class="badge ${g != null ? (g < 10 ? 'badge-red' : 'badge-green') : 'badge-blue'}" style="opacity: ${g != null ? 1 : 0.3}">
                                            ${g != null ? g : '--'}
                                        </div>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
