var currentTab = "services";
var curr_form_obj = -100;

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

var data = {
  feedbacks: {},
  services: {},
  profile: {},
};

var navbarDom = {
  container: document.getElementById("header"),
  notification: document.getElementById("notification"),
  menu: document.getElementById("menu-button"),
};

var mainLoader = document.getElementById("main-loader");
var warningModal = {
  container: document.getElementById("warning-modal"),
  content: document.getElementById("warning-modal-content"),
};
warningModal.switch = $(warningModal.container);

var normalModal = {
  container: document.getElementById("normal-modal"),
  content: document.getElementById("normal-modal-content"),
  title: document.getElementById("normal-modal-title"),
  register: document.getElementById("normal-modal-register"),
};
normalModal.switch = $(normalModal.container);

var contentDom = {
  container: document.getElementById("content"),
  lastCheck: document.getElementById("last-refreshed"),
  header: {
    title: document.getElementById("content-title"),
    left: document.getElementById("content-header-left"),
    right: document.getElementById("content-header-right"),
  },
};

var servicesDom = {
  container: document.getElementById("services-container"),
  content: document.getElementById("services-content-body"),
};

var profileDom = {
  container: document.getElementById("profile-container"),
  content: document.getElementById("profile-content-body"),
};

var feedbacksDom = {
  content: document.getElementById("feedbacks-content-body"),
  container: document.getElementById("feedbacks-container"),
};

function setMainContentFromSideBar(value, others) {
  navbarDom.menu.click();
  setMainContent(value, others);
}

function setMainContent(value, others = {}) {
  mainLoader.hidden = false;
  servicesDom.container.hidden = true;
  profileDom.container.hidden = true;
  feedbacksDom.container.hidden = true;
  if (value == "services") {
    servicesDom.container.hidden = false;
  } else if (value == "profile") {
    profileDom.container.hidden = false;
    markAsReadProfile("cleaner_read","cleaners")
  } else if (value == "feedbacks") {
    feedbacksDom.container.hidden = false;
  }
  mainLoader.hidden = true;
}

window.addEventListener("resize", handleResize);

function handleResize(e) {
  contentDom.container.style["height"] =
    window.innerHeight - navbarDom.container.clientHeight + "px";
}

function textResize(e) {
  e.style.height = "auto";
  e.style.height = e.scrollHeight + "px";
  e.innerHeight = e.scrollHeight;
}

let service_mapper = {
  booked: "Not Completed",
  completed: "Completed",
};

let feedbacks_mapper = {
  given: "Given",
  "not-given": "Not Given",
};

async function setFeedBacks() {
  data.feedbacks = util.classify(
    await (await fetch("./get_my_feedbacks")).json(),
    "status"
  );
  //
  let names = Object.keys(data.feedbacks).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";

    let count = countUnread("cleaner_read", "feedbacks", name);
    if (count > 0) {
      suffix = `<span class="m-1 position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary" id="feedbacks-${name}-count">${count}</span>`;
    }

    tabName += `<li class="nav-item">
    <a class="nav-link" id="tt-${name}" data-toggle="tab" href="#tc-${name}" role="tab" aria-controls="tc-${name}" aria-selected="true"><span class="position-relative">${feedbacks_mapper[name]}&nbsp;${suffix}</span></a>
  </li>`;

    tabContent += ` <div class="tab-pane fade show ${
      t1 == 0 ? "active" : ""
    } p-3" id="tc-${name}" role="tabpanel" aria-labelledby="tc-${name}-tab">
      <h4 class="text-center">${feedbacks_mapper[name]} feedbacks</h4>
      <table class="table table-hover table-head-fixed"><thead><tr>
      <th>S-NO</th>
      <th>User Id</td>
      <th>Rating</th>
      <th>Feedback</th>
      <th>Service Address</th>
      <th>Time Slot</th>
    </tr></thead>`;
    let arr = data.feedbacks[name];
    arr.forEach((e, i) => {
      tabContent += `<tr class="read-${e.cleaner_read} style="cursor:pointer" onclick="setFeedBackModal('${name}',${i});markAsRead(this,'cleaner_read', 'feedbacks','${name}',${i})">
        <td>${e.s_no}</td>          
        <td>${e.user_id}</td>        
        <td>${e.rating}</td>              
        <td style="word-wrap:break-word;word-break:break-word">${e.feedback}</td>           
        <td style="word-wrap:break-word;word-break:break-word">${e.address},${e.pincode}</td>           
        <td>${e.time_from} - ${e.time_to}</td>                           
      <tr>`;
    });
    tabContent += `</table></div>`;
  });
  tabName += `</ul>`;
  tabContent += `</div>`;
  feedbacksDom.content.innerHTML = tabName + tabContent;
}

async function setProfile() {
  data.cleaners = [await (await fetch("./get_cleaner_profile")).json()];

  let e = data.cleaners[0];

  profileDom.content.innerHTML = `<dl class="row"><dt class="fw-bold col-lg-4 col-md-4 col-sm-4">Cleaner ID</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.cleaner_id
    }</dd>
    <dt class="fw-bold col-lg-4 col-md-4 col-sm-4">Full Name</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.first + " " + e.last
    }</dd>
    <dt class="fw-bold col-lg-4 col-md-4 col-sm-4">DOB</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.dob
    }</dd>
    <dt class="fw-bold col-lg-4 col-md-4 col-sm-4">Gender</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.gender
    }</dd>
    <dt class="fw-bold col-lg-4 col-md-4 col-sm-4">Mobile</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.phone
    }</dd>
    <dt class="fw-bold col-lg-4 col-md-4 col-sm-4">Profile Status</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.profile_status == "approved"? "<span class='badge badge-success text-light'><i class='fas fa-check-circle'></i>&nbsp;Approved</span>": "<span class='badge badge-warning text-light'><i class='fas fa-question-circle'></i></i>&nbsp;"+e.profile_status+"</span>"
    }</dd>
    <dt class="fw-bold col-lg-4 col-md-4 col-sm-4">Registered At</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.created
    }</dd>
    <dt class="fw-bold col-lg-4 col-md-4 col-sm-4">Last Modified</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.modified
    }</dd></dl>
    `;
}

async function setServices() {
  data.services = util.classify(
    await (await fetch("./get_my_services")).json(),
    "status"
  );

  let names = Object.keys(data.services).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";
    let count = countUnread("cleaner_read", "services", name);
    if (count > 0) {
      suffix = `<span class="m-1 position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary" id="services-${name}-count">${count}</span>`;
    }

    tabName += `<li class="nav-item">
              <a class="nav-link" id="tt-${name}" data-toggle="tab" href="#tc-${name}" role="tab" aria-controls="tc-${name}" aria-selected="true"><span class="position-relative">${service_mapper[name]}&nbsp;${suffix}</span></a>
            </li>`;
    tabContent += ` <div class="tab-pane fade show ${
      t1 == 0 ? "active" : ""
    } p-3" id="tc-${name}" role="tabpanel" aria-labelledby="tc-${name}-tab">
      <h4 class="text-center">${service_mapper[name]} Services</h4>
      <table class="table table-hover table-head-fixed"><thead><tr>
      <th>S-NO</th>
      <th>User Id</td>
      <th>Address</th>
      <th>Pin Code</th>
      <th>From</th>
      <th>To</th>
    </tr></thead>`;
    let arr = data.services[name];
    arr.forEach((e, i) => {
      tabContent += `<tr class="read-${e.cleaner_read} style="cursor:pointer" onclick="setServiceModal('${name}',${i});markAsRead(this,'cleaner_read', 'services','${name}',${i})">
        <td>${e.s_no}</td>          
        <td>${e.user_id}</td>        
        <td  style="word-wrap:break-word;word-break:break-word">${e.address}</td>              
        <td>${e.pincode}</td>              
        <td>${e.time_from}</td>              
        <td>${e.time_to}</td>              
      <tr>`;
    });
    tabContent += `</table></div>`;
  });
  tabName += `</ul>`;
  tabContent += `</div>`;
  servicesDom.content.innerHTML = tabName + tabContent;
}

function setServiceModal(type, index) {
  let t = data.services[type][index];

  normalModal.register.innerHTML = "Update Status";
  normalModal.register.className = "btn btn-flat btn-primary";
  normalModal.register.disabled = false;
  normalModal.register.hidden = true;

  let temp = findTypeAndIndex("feedbacks", "service_id", t.service_id);

  normalModal.title.innerHTML = `Service Details - Registered at ${t.created}`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Service ID</dt>
    <dd class="col-sm-8">${t.service_id}</dd>
    <dt class="col-sm-4">User Id</dt>
    <dd class="col-sm-8">${t.user_id}</dd>
    <dt class="col-sm-4">Rooms</dt>
    <dd class="col-sm-8">${t.room_count}</dd>
    <dt class="col-sm-4">Address</dt>
    <dd class="col-sm-8">${t.address}</dd>
    <dt class="col-sm-4">Pin Code</dt>
    <dd class="col-sm-8">${t.pincode}</dd>
    <dt class="col-sm-4">Time Slot From</dt>
    <dd class="col-sm-8">${t.time_from}</dd>
    <dt class="col-sm-4">Time Slot To</dt>
    <dd class="col-sm-8">${t.time_to}</dd>
    <dt class="col-sm-4">Created At</dt>
    <dd class="col-sm-8">${t.created}</dd>
    <dt class="col-sm-4">Last Modified</dt>
    <dd class="col-sm-8">${t.modified}</dd>
    <dt class="col-sm-4">Status</dt>
    <dd class="col-sm-8">
    ${
      t.status == "booked"
        ? `<select class="form-control" 
        onchange="serviceModalChangeHandler(this,'status','${type}',${index})">
          <option ${
            t.status == "not-complted" ? "selected" : ""
          } value="not-completed">Not Completed</option>
          <option ${
            t.status == "completed" ? "selected" : ""
          } value="completed">Completed</option>
          </select>`
        : service_mapper[t.status]
    }
    </dd>
  </dl>
    `;

  if (t.status == "completed") {
    normalModal.content.innerHTML += `<button onclick="setFeedBackModal('${temp[0]}',${temp[1]})" class="btn btn-flat btn-primary">View FeedBack Details</button>`;
  }

  normalModal.register.onclick = () => {
    submitServiceModal(type, index);
  };
  normalModal.switch.modal("show");
}

function setFeedBackModal(type, index) {
  let t = data.feedbacks[type][index];

  let temp = findTypeAndIndex("services", "service_id", t.service_id);

  normalModal.register.hidden = true;
  normalModal.title.innerHTML = `Review for service completed ${t.modified}`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Service ID</dt>
    <dd class="col-sm-8">${t.service_id}</dd>
    <dt class="col-sm-4">Rating</dt>
    <dd class="col-sm-8">${t.rating}</dd>
    <dt class="col-sm-4">Feed back</dt>
    <dd class="col-sm-8">${t.feedback}</dd>
    <dt class="col-sm-4">User Id</dt>
    <dd class="col-sm-8">${t.user_id}</dd>
    <dt class="col-sm-4">Service Address</dt>
    <dd class="col-sm-8">${t.address}</dd>
    <dt class="col-sm-4">Service Pin Code</dt>
    <dd class="col-sm-8">${t.pincode}</dd>
    <dt class="col-sm-4">Time Slot</dt>
    <dd class="col-sm-8">${t.time_from} - ${t.time_to}</dd>
  </dl>

    <button onclick="setServiceModal('${temp[0]}',${temp[1]})" class="btn btn-flat btn-primary">View Service Details</button>

    `;
  normalModal.switch.modal("show");
}

function findTypeAndIndex(tab, key, value) {
  let d = data[tab];
  let names = Object.keys(d) || [];
  for (var e of names) {
    let arr = d[e];
    for (var f in arr) {
      if (arr[f][key] == value) return [e, f];
    }
  }
  return [null, null];
}

function submitServiceModal(type, index) {
  let e = data.services[type][index];

  if (e.status !== e.temp_status) {
    normalModal.register.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Updating..`;
    normalModal.register.disabled = true;

    fetch("./modify_my_service", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        service_id: e.service_id,
        user_uid: e.user_uid,
        status: e.temp_status || e.status,
      }),
    })
      .then((res) => res.json())
      .then(async (data) => {
        if (data.status == "success") {
          e.status = e.temp_status || e.status;
          e.temp_status = undefined;
          normalModal.switch.modal("hide");
          normalModal.register.hidden = true;
          setServices();
          setFeedBacks();
          normalModal.register.className = "btn btn-flat btn-success";
          normalModal.register.innerHTML = `<i class="fas fa-check-circle"></i>&nbsp;Updated Successfully`;
        } else {
          normalModal.register.className = "btn btn-flat btn-danger";
          normalModal.register.innerHTML = `<i class="fas fa-times-circle"></i>&nbsp;Updated unsuccessfully`;
        }
        normalModal.register.disabled = false;
        normalModal.register.onclick = () => {
          normalModal.register.hidden = true;
          normalModal.switch.modal("hide");
        };
      });
  }
}

let notification_mapper = {
  pending: " newly registered",
  available: "available for booking",
  "not-available": "not available for booking",
  booked: "newly booked ",
  completed: "completed ",
  given: " newly reviewed ",
  "not-given": " not completed ",
  answered: " answered ",
  "not-answered": " not Answered",
  resolved: " newly resolved ",
  "not-resolved": " not Resolved",
  pending: " status was under pending",
  approved: " was approved ",
  denied: " was rejected",
};

function setNotification() {
  let str = "";
  let sum = 0;
  if (data.cleaners[0]["cleaner_read"] === false) {
    str += ` <div class="dropdown-divider"></div>
                <a onclick="setMainContent('profile')" href="#" class="dropdown-item">
                    Profile ${notification_mapper[data.cleaners[0].profile_status]} by admin
                </a>`;
    sum += 1;
  }

  Object.keys(data.feedbacks).forEach((name) => {
    let count = countUnread("cleaner_read", "feedbacks", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('feedbacks')" href="#" class="dropdown-item">
                      ${count} services ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  Object.keys(data.services).forEach((name) => {
    let count = countUnread("cleaner_read", "services", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('services')" href="#" class="dropdown-item">
                      ${count} services ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  

  if (sum > 0) {
    navbarDom.notification.innerHTML = `
    <a class="nav-link" data-toggle="dropdown" href="#">
      <i class="far fa-bell"></i>
      <span class="badge badge-primary navbar-badge text-light">${sum}</span>
    </a>
    <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
      <span class="dropdown-item dropdown-header">${sum} Notifications</span>${str}</div>`;
  }
}

window.addEventListener("load", async (e) => {
  await setServices();
  await setFeedBacks();
  await setProfile();
  await setNotification();
  setMainContent(currentTab);

  handleResize();
});

var serviceModalChangeHandler = (element, input, type, index) => {
  data.services[type][index]["temp_" + input] = element.value;
  normalModal.register.hidden = false;
};

const util = {
  classify(objArr, by) {
    let out = {};
    for (let i of objArr) (out[i[by]] || (out[i[by]] = [])).push(i);
    return out;
  },
  isEqual(a, b) {
    if (Object.keys(a).length !== Object.keys(b).length) return false;
    for (let i in a) if (a[i] != b[i]) return false;
    return true;
  },
  changeIndex(a, b) {
    let o = [];
    for (let i in a) if (!this.isEqual(a[i], b[i])) o.push(i);
    return o;
  },
  findFirst(objArr, key, value) {
    for (let i of objArr) {
      if (i[key] == value) return i;
    }
    return {};
  },
  getEvtNames(str = " ", user_id) {
    let s = str.split(", ");
    s.sort();
    let t = [];
    for (let i of s) {
      if (i.length > 1) {
        t.push(
          `<a href="#" onclick="goToModal('candidate','${i.trim()}','${user_id}')">${
            data.events[i.trim()][0].name
          }</a>`
        );
      }
    }
    return t.join(", ");
  },
};
