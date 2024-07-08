document.addEventListener('DOMContentLoaded', async () => {
    await navbarEmail();
    await authorize();
    await fillAllUsers();
    await fillUser();
    await newUserForm();
    await deleteModalHandler();
    await editModalHandler();
});

async function getCurrentUser(){
    return await fetch("/api/userinfo").then(cu => cu.json());
}

const userAdminSelect = document.getElementById("userAdminSelect");
async function authorize() {
    const currentUser = await getCurrentUser();
    if (currentUser.roles.find(role => role.role === "ROLE_ADMIN") !== undefined)  {
        userAdminSelect.innerHTML =
            `<li class="nav-item">
                   <a href="#admin-panel" data-bs-toggle="pill" class="nav-link active no-refresh" role="tab" aria-current="true">
                        Admin</a>
             </li>
             <li class="nav-item">
                   <a href="#userInfo" data-bs-toggle="pill" class="nav-link no-refresh" role="tab" aria-current="false">
                        User
                   </a>
             </li>`
    } else {
        document.getElementById("admin-panel").setAttribute("class", "tab-pane fade");
        document.getElementById("userInfo").setAttribute("class", "tab-pane fade show active");
        userAdminSelect.innerHTML =
             `<li class="nav-item">
                   <a href="#userInfo" data-bs-toggle="pill" class="nav-link active no-refresh" role="tab" aria-current="false">
                        User
                   </a>
             </li>`
    }
}

async function navbarEmail() {
    const currentUser = await getCurrentUser();
    document.getElementById("currentUserEmailNavbar").innerHTML =
        `<strong>${currentUser.username}</strong>
                 with roles: 
                 ${currentUser.roles.map(role => role.role.substring(5).concat(" ")).toString().replaceAll(",", "")}`;
}
