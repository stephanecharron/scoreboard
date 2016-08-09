grammar Scoreboard;

field:(start | end | print | addGoal);

start:'Start: 'TeamDelimiter(home=Name)TeamDelimiter (visit) TeamDelimiter(visitor=Name)TeamDelimiter;
end: 'End';
addGoal:time=Int TeamDelimiter(team=Name)TeamDelimiter playerName=Name;
print: 'print';

Name:('A'..'Z' | 'a'..'z' | ' ')+ ;
TeamDelimiter:'\'';
visit:'vs.';
Int:('0'..'9')+;
