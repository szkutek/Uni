% tree --> ['*'],!.
% tree --> ['('], tree, tree, [')'].


% phrase( node(leaf, node(leaf,leaf)) , ['(', *, '(', *, *, ')', ')']).

tree(leaf) --> ['*'],!.
tree(node(A,B)) --> ['('], tree(A), tree(B), [')'].

% ?- expand_term( (tree --> ['('], tree, tree, [')'] ), T).
% T = (tree(['('|X], Y):-
%     tree(X, Z),
%     tree(Z, K),
%     K=[')'|Y]).

% ?- expand_term( ( tree(node(A,B)) -->
%     ['('], tree(A), tree(B), [')']  ),T).
% T = (tree(node(A, B), ['('|X], Y):-
%     tree(A, X, Z),
%     tree(B, Z, K),
%     K=[')'|Y]).
