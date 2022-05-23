/**
 * 
 */
 
const content = document.getElementById('content')
const date = document.getElementById('date')
const memberCount = document.getElementById('memberCount')
const addedBtn = document.getElementById('addedBtn')
const deletedBtn = document.getElementById('deletedBtn')
const list = document.getElementById('list')

const listContent = []

class RenderFeature{
  append() {

  }
  render() {
    // 渲染頁面的list
    let htmlStr = ''
  
    listContent.forEach(function (item) {
      htmlStr = htmlStr + `
      <div class="item">
        <div>
        <p>日期：${item.date}</p>
        <p>人數：${item.memberCount}</p>
        <p>備註：${item.content}</p>
        </div>
      </div>
      `
    })
  
    list.innerHTML = htmlStr
  }
}


// function 
function render() {
  // 渲染頁面的list
  let htmlStr = ''
  listContent.forEach(function (item) {
    htmlStr = htmlStr + `
    <div class="item">
      <div>
      <p>日期：${item.date}</p>
      <p>人數：${item.memberCount}</p>
      <p>備註：${item.content}</p>
      </div>
    </div>
    `
  })

  list.innerHTML = htmlStr
}

addedBtn.addEventListener('click', function () {

  listContent.unshift({
    content: content.value,
    date : date.value,
    memberCount : memberCount.value
  })

  render()
  
})

deletedBtn.addEventListener('click', function () {
  listContent.shift()

  render()
})