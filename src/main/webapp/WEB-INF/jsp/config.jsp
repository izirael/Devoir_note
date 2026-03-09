<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Configuration - Grade System</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Configuration</h1>
            <nav>
                <a href="/" class="nav-link">Back to Grades</a>
            </nav>
        </header>

        <div class="glass-card" style="max-width: 600px; margin: 0 auto;">
            <h2 style="margin-bottom: 2rem;">Calculation Parameters</h2>
            
            <c:forEach items="${parametres}" var="parametre">
                <form action="/config/update" method="POST">
                    <input type="hidden" name="id" value="${parametre.id}">
                    
                    <div class="form-group">
                        <label>Multi-correction Operator</label>
                        <select name="idOperateur">
                            <c:forEach items="${operateurs}" var="op">
                                <option value="${op.id}" ${op.id == parametre.operateur.id ? 'selected' : ''}>
                                    ${op.nom} (${op.symbole})
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div style="display: flex; gap: 1rem; color: var(--text-secondary); margin-bottom: 1.5rem;">
                        <small>Value Left: ${parametre.valeurGauche}</small>
                        <small>Value Right: ${parametre.valeurDroite}</small>
                    </div>

                    <button type="submit">Update Configuration</button>
                </form>
            </c:forEach>

            <c:if test="${empty parametres}">
                <p style="color: var(--text-secondary)">No parameters configured yet.</p>
            </c:if>
        </div>
    </div>
</body>
</html>
