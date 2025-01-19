db = db.getSiblingDB("park");

db.createUser({
    user: "root",
    pwd: "pass",
    roles: [{role: "readWrite", db: "park"}]
});

print("Usuário 'root' criado no banco de dados 'park'");