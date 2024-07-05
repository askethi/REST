
async function fillAllUsers() {
    const usersTable = document.getElementById("usersTableId");
    const users = await fetch("/api/users").then(uss => uss.json());

    usersTable.innerHTML = "";
    for (let user of users) {
           usersTable.innerHTML +=
            `<tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
               <td>${user.roles.map(role => role.role.substring(5).concat(" ")).toString().replaceAll(",", "")}</td>
                <td>
                    <button class="btn btn-primary"
                            data-bs-toggle="modal"
                            data-bs-target="#editModal"
                            data-user-id="${user.id}">
                        Edit</button>
                </td>
                <td>
                    <button class="btn btn-danger"
                            data-bs-toggle="modal"
                            data-bs-target="#deleteModal"
                            data-user-id="${user.id}">
                        Delete</button>
                </td>
            </tr>`;
    }
}

async function fillUser(){
    const currentUser = await getCurrentUser();
    document.getElementById("currentUserTable").innerHTML =
        `<tr>
            <td>${currentUser.id}</td>
            <td>${currentUser.firstName}</td>
            <td>${currentUser.lastName}</td>
            <td>${currentUser.age}</td>
            <td>${currentUser.email}</td>
            <td>${currentUser.roles.map(role => role.role.substring(5).concat(" ")).toString().replaceAll(",", "")}</td>
        </tr>`
}