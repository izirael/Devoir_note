PS D:\Study\Programation\MrNaina\Note_V2> New-Item readme.md


    Répertoire : D:\Study\Programation\MrNaina\Note_V2


Mode                 LastWriteTime         Length Name                                            
----                 -------------         ------ ----                                            
-a----          3/9/2026   8:23 AM              0 readme.md                                       

PS D:\Study\Programation\MrNaina\Note_V2> git commit -m "Premier commit : initialisation du projet"
[master (root-commit) 6c6641a] Premier commit : initialisation du projet
 1 file changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 readme.md
PS D:\Study\Programation\MrNaina\Note_V2> git checkout -b exo_notes   
Switched to a new branch 'exo_notes'
PS D:\Study\Programation\MrNaina\Note_V2> New-Item .gitignore


    Répertoire : D:\Study\Programation\MrNaina\Note_V2


Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
-a----          3/9/2026   8:26 AM              0 .gitignore


PS D:\Study\Programation\MrNaina\Note_V2> git add .gitignore
PS D:\Study\Programation\MrNaina\Note_V2> git commit -m "Ajout du fichier .gitignore"
[exo_notes 1bfc5d1] Ajout du fichier .gitignore
 1 file changed, 5 insertions(+)
 create mode 100644 .gitignore
PS D:\Study\Programation\MrNaina\Note_V2> 