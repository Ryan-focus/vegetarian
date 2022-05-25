const blurr = document.querySelector(".blur");
const input = document.querySelectorAll("input");
let blurEffect = false;
const submit = document.querySelector(".submit");

const blurRemove = () => {
  if (blurEffect === true) {
    blurr.classList.remove("blur-new");
  }
};
const blurAdd = () => {
  blurr.classList.add("blur-new");
  blurEffect = true;
};

for (const inp of input) {
  inp.addEventListener("click", blurAdd);
}

blurr.addEventListener("click", blurRemove);
submit.addEventListener("click", blurRemove);
