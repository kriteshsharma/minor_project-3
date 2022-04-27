//ADD BUTTON DISABLING INSIDE ADD MODAL

let i1 = document.querySelector("#add_inv_name");
let i2 = document.querySelector("#add_inv_dd");
let i3 = document.querySelector("#add_inv_cn");
let i4 = document.querySelector("#add_inv_ino");
let i5 = document.querySelector("#add_inv_amt");
let add_btn = document.querySelector(".add_add_btn");


add_btn.disabled = true; //setting button state to disabled

i1.addEventListener("change", stateHandle);
i2.addEventListener("change", stateHandle);
i3.addEventListener("change", stateHandle);
i4.addEventListener("change", stateHandle);
i5.addEventListener("change", stateHandle);

function stateHandle() {
    if (i1.value != "" && i2.value != "" && i3.value != "" && i4.value != "" && i5.value != "") {
        add_btn.disabled = false; //button is enabled
    } else {
        add_btn.disabled = true; //button remains disabled
    }
}

//CHECK ALL CHECKBOXES ON COLUMN CHECK

function check(checked = true) {
    const cbs = document.querySelectorAll('input[name="row-check"]');
    cbs.forEach((cb) => {
        cb.checked = checked;
    });
}
const btn = document.querySelector('#check_all');
btn.onclick = checkAll;
function checkAll() {
    check();
    // reassign click event handler
    this.onclick = uncheckAll;
}
function uncheckAll() {
    check(false);
    // reassign click event handler
    this.onclick = checkAll;
}

