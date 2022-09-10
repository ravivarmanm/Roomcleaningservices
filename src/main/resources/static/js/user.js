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
  questions: {},
  tickets: {},
  cleaners: {},
};

var navbarDom = {
  container: document.getElementById("header"),
  menu: document.getElementById("menu-button"),
  notification: document.getElementById("notification"),
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

var profileDom = {
  container: document.getElementById("profile-container"),
  content: document.getElementById("profile-content-body"),
};

var servicesDom = {
  container: document.getElementById("services-container"),
  content: document.getElementById("services-content-body"),
};

var ticketsDom = {
  content: document.getElementById("tickets-content-body"),
  container: document.getElementById("tickets-container"),
};

var feedbacksDom = {
  content: document.getElementById("feedbacks-content-body"),
  container: document.getElementById("feedbacks-container"),
};

var questionsDom = {
  content: document.getElementById("questions-content-body"),
  container: document.getElementById("questions-container"),
};

function setMainContentFromSideBar(value, others) {
  navbarDom.menu.click();
  setMainContent(value, others);
}

function setMainContent(value, others = {}) {
  mainLoader.hidden = false;
  servicesDom.container.hidden = true;
  ticketsDom.container.hidden = true;
  feedbacksDom.container.hidden = true;
  questionsDom.container.hidden = true;
  profileDom.container.hidden = true;

  if (value == "services") {
    servicesDom.container.hidden = false;
  } else if (value == "tickets") {
    ticketsDom.container.hidden = false;
  } else if (value == "feedbacks") {
    feedbacksDom.container.hidden = false;
  } else if (value == "questions") {
    questionsDom.container.hidden = false;
  } else if (value == "profile") {
    profileDom.container.hidden = false;
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
  pending: "Sent for checking availability",
  available: "Available Service",
  "not-available": "Not Available ",
  booked: "Booked ",
  completed: "Completed ",
};

let feedbacks_mapper = {
  given: "Given",
  "not-given": "Not Given",
};

let questions_mapper = {
  answered: "Answered",
  "not-answered": "Not Answered",
};

let ticket_status_map = {
  resolved: "Resolved",
  "not-resolved": "Not Resolved",
};

async function setProfile() {
  data.users = [await (await fetch("./get_user_profile")).json()];

  let e = data.users[0];
  profileDom.content.innerHTML = `<dl class="row"><dt class="fw-bold col-lg-4 col-md-4 col-sm-4">User ID</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.user_id
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
    <dt class="fw-bold col-lg-4 col-md-4 col-sm-4">Email</dt>
    <dd class="fw-bold col-lg-8 col-md-8 col-sm-8" style="color:#012970;"> ${
      e.mail
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

async function setFeedBacks() {
  data.feedbacks = util.classify(
    await (await fetch("./get_user_feedbacks")).json(),
    "status"
  );
  //
  let names = Object.keys(data.feedbacks).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";

    let count = countUnread("user_read", "feedbacks", name);
    if (count > 0) {
      suffix = `<span class="m-1 position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary" id="feedbacks-${name}-count">${count}</span>`;
    }

    tabName += `<li class="nav-item">
              <a class="nav-link" id="tt-${name}" data-toggle="tab" href="#tc-${name}" role="tab" aria-controls="tc-${name}" aria-selected="true"><span class="position-relative">${feedbacks_mapper[name]}&nbsp;${suffix}</span></a>
            </li>`;
    tabContent += ` <div class="tab-pane fade show ${
      t1 == 0 ? "active" : ""
    } p-3" id="tc-${name}" role="tabpanel" aria-labelledby="tc-${name}-tab">
      <h4 class="text-center">${feedbacks_mapper[name]} Reviews</h4>
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
      tabContent += `<tr class="read-${e.user_read}" style="cursor:pointer" onclick="setFeedBackModal('${name}',${i});markAsRead(this,'user_read', 'feedbacks','${name}',${i})">
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

  let t_index = -1;
  for (var i in data.feedbacks["not-given"] || []) {
    let e = data.feedbacks["not-given"][i];
    if (!e.user_read) {
      t_index = i;
      break;
    }
  }
  if (t_index > -1) {
    setFeedBackModal("not-given", t_index);
  }
}

async function setServices() {
  data.services = util.classify(
    await (await fetch("./get_user_services")).json(),
    "status"
  );

  let names = Object.keys(data.services).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";
    let count = countUnread("user_read", "services", name);
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
      tabContent += `<tr class="read-${e.user_read}" style="cursor:pointer" onclick="setServiceModal('${name}',${i});markAsRead(this,'user_read', 'services','${name}',${i})">
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

  let temp = findTypeAndIndex("feedbacks", "service_id", t.service_id);

  normalModal.register.innerHTML = "Confirm Service Registration";
  normalModal.register.className = "btn btn-flat btn-primary";
  normalModal.register.disabled = false;
  normalModal.register.hidden = true;
  
  if(t.status == "not-available"){
	normalModal.register.innerHTML = "Cancel Service Registration";
  	normalModal.register.className = "btn btn-flat btn-danger";
}

  normalModal.title.innerHTML = `Service At ${t.created}`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Service ID</dt>
    <dd class="col-sm-8">${t.service_id}</dd>
    <dt class="col-sm-4">User Id</dt>
    <dd class="col-sm-8">${t.user_id}</dd>
    ${t.status != "not-available" && t.status != "pending"?`<dt class="col-sm-4">Cleaner Id</dt>
    <dd class="col-sm-8">${t.cleaner_id}</dd>`:""}
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
    <dt class="col-sm-4">${t.status}</dt>
    </dl>
    `;

  if (t.status == "completed") {
    normalModal.content.innerHTML += `<button onclick="setFeedBackModal('${temp[0]}',${temp[1]})" class="btn btn-flat btn-primary">View Review Details</button>`;
  }

  if (t.status == "available") {
    normalModal.register.innerHTML = `<a class="text-light" href="./user_payment?service_id=${t.service_id}&user_uid=${t.user_uid}">Confirm Service Registration</a>`;
    normalModal.register.hidden = false;
  }
  
 if (t.status == "not-available") {
    normalModal.register.innerHTML = `<a class="text-light" href="./user_cancel_service?service_id=${t.service_id}&user_uid=${t.user_uid}">Cancel Registered Service</a>`;
    normalModal.register.hidden = false;
  }
  normalModal.switch.modal("show");
}

function setFeedBackModal(type, index) {
  let t = data.feedbacks[type][index];

  let temp = findTypeAndIndex("services", "service_id", t.service_id);

  normalModal.register.innerHTML = "Submit Review";
  normalModal.register.className = "btn btn-flat btn-primary";
  normalModal.register.disabled = false;
  normalModal.register.hidden = true;

  normalModal.title.innerHTML = `Review for service completed at ${t.created}`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Service ID</dt>
    <dd class="col-sm-8">${t.service_id}</dd>
    <dt class="col-sm-4">User Id</dt>
    <dd class="col-sm-8">${t.user_id}</dd>
    <dt class="col-sm-4">Service Address</dt>
    <dd class="col-sm-8">${t.address}</dd>
    <dt class="col-sm-4">Service Pin Code</dt>
    <dd class="col-sm-8">${t.pincode}</dd>
    <dt class="col-sm-4">Time Slot</dt>
    <dd class="col-sm-8">${t.time_from} - ${t.time_to}</dd>
    <dt class="col-sm-4">Rating</dt>
    <dd class="col-sm-8">
      ${
        t.status == "not-given"
          ? `     <div class="form-check form-check-inline">
      <input value="0" onchange="reviewModalChangeHandler(this,'rating','${type}',${index})" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
      <label class="form-check-label" for="flexRadioDefault2">
          0
      </label>
    </div>
    <div class="form-check form-check-inline">
      <input value="1" onchange="reviewModalChangeHandler(this,'rating','${type}',${index})" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
      <label class="form-check-label" for="flexRadioDefault2">
          1
      </label>
    </div>
    <div class="form-check form-check-inline">
    <input value="2" onchange="reviewModalChangeHandler(this,'rating','${type}',${index})" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
    <label class="form-check-label" for="flexRadioDefault2">
        2
    </label>
  </div>
  <div class="form-check form-check-inline">
    <input value="3" onchange="reviewModalChangeHandler(this,'rating','${type}',${index})" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
    <label class="form-check-label" for="flexRadioDefault2">
        3
    </label>
  </div>
  <div class="form-check form-check-inline">
    <input value="4" onchange="reviewModalChangeHandler(this,'rating','${type}',${index})" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
    <label class="form-check-label" for="flexRadioDefault2">
        4
    </label>
  </div>
  <div class="form-check form-check-inline">
    <input value="5" onchange="reviewModalChangeHandler(this,'rating','${type}',${index})" class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2">
    <label class="form-check-label" for="flexRadioDefault2">
        5
    </label>
  </div>
`
          : t.rating
      }
    </dd>
    <dt class="col-sm-4">Review</dt>
    <dd class="col-sm-12">
      ${
        t.status == "not-given"
          ? `<textarea oninput="reviewModalChangeHandler(this,'feedback','${type}',${index});textResize(this)" style="width:100%"></textarea>`
          : t.feedback
      }
    </dd>
  </dl>
    <button onclick="setServiceModal('${temp[0]}',${
    temp[1]
  })" class="btn btn-flat btn-primary">View Service Details</button>
    `;

  normalModal.register.onclick = () => {
    submitReviewModal(type, index);
  };
  normalModal.switch.modal("show");
}

var reviewModalChangeHandler = (element, input, type, index) => {
  data.feedbacks[type][index]["temp_" + input] = element.value;
  normalModal.register.hidden = false;
};

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

function submitReviewModal(type, index) {
  let e = data.feedbacks[type][index];

  if (e.rating !== e.temp_rating || e.feedback !== e.temp_feedback) {
    normalModal.register.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Submitting..`;
    normalModal.register.disabled = true;

    fetch("./modify_user_review", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        service_id: e.service_id,
        user_uid: e.user_uid,
        rating: e.temp_rating || e.rating,
        feedback: e.temp_feedback || e.rating,
      }),
    })
      .then((res) => res.json())
      .then(async (data) => {
        if (data.status == "success") {
          e.rating = e.temp_rating || e.rating;
          e.feedback = e.temp_feedback || e.rating;
          e.temp_rating = undefined;
          e.temp_feedback = undefined;
          setFeedBacks();
          normalModal.register.className = "btn btn-flat btn-success";
          normalModal.register.innerHTML = `<i class="fas fa-check-circle"></i>&nbsp;Submitted Successfully`;
        } else {
          normalModal.register.className = "btn btn-flat btn-danger";
          normalModal.register.innerHTML = `<i class="fas fa-times-circle"></i>&nbsp;Submitted unsuccessfully`;
        }
        normalModal.register.disabled = false;
        normalModal.register.onclick = () => {
          normalModal.register.hidden = true;
          normalModal.switch.modal("hide");
        };
      });
  }
}

function submitServiceModal(type, index) {
  let e = data.services[type][index];

  if (e.status !== e.temp_status) {
    fetch("./modify_user_service", {
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
          Toast.fire({
            icon: "success",
            title: "Service Completed successfully",
          });
        } else {
          Toast.fire({
            icon: "error",
            title: "Service Completed unsuccessfully",
          });
        }
      });
  }
}

async function setQuestions() {
  data.questions = util.classify(
    await (await fetch("./get_user_questions")).json(),
    "status"
  );

  let names = Object.keys(data.questions).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";
    let count = countUnread("user_read", "questions", name);
    if (count > 0) {
      suffix = `<span class="m-1 position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary" id="questions-${name}-count">${count}</span>`;
    }

    tabName += `<li class="nav-item">
              <a class="nav-link" id="tt-${name}" data-toggle="tab" href="#tc-${name}" role="tab" aria-controls="tc-${name}" aria-selected="true"><span class="position-relative">${questions_mapper[name]}&nbsp;${suffix}</span></a>
            </li>`;
    tabContent += ` <div class="tab-pane fade show ${
      t1 == 0 ? "active" : ""
    } p-3" id="tc-${name}" role="tabpanel" aria-labelledby="tc-${name}-tab">
      <h4 class="text-center">${questions_mapper[name]} Feedbacks</h4>
      <table class="table table-hover table-head-fixed"><thead><tr>
      <th>S-NO</th>
      <th>User Id</td>
      <th>question</th>
      <th>Answer</th>
      <th>Service Address</th>
      <th>Time Slot</th>
    </tr></thead>`;
    let arr = data.questions[name];
    arr.forEach((e, i) => {
      tabContent += `<tr class="read-${e.user_read}" style="cursor:pointer" onclick="setQuestionModal('${name}',${i});markAsRead(this,'user_read', 'questions','${name}',${i})">
        <td>${e.s_no}</td>          
        <td>${e.user_id}</td>        
        <td style="word-wrap:break-word;word-break:break-word">${e.question}</td>           
        <td style="word-wrap:break-word;word-break:break-word">${e.answer}</td>              
        <td>${e.address},${e.pincode}</td>           
        <td>${e.time_from} - ${e.time_to}</td>                           
      <tr>`;
    });
    tabContent += `</table></div>`;
  });
  tabName += `</ul>`;
  tabContent += `</div>`;
  questionsDom.content.innerHTML = tabName + tabContent;
}

function setQuestionModal(type, index) {
  let t = data.questions[type][index];

  let temp = findTypeAndIndex("services", "service_id", t.service_id);

  normalModal.register.innerHTML = "Submit Feedback";
  normalModal.register.className = "btn btn-flat btn-primary";
  normalModal.register.disabled = false;
  normalModal.register.hidden = true;
  normalModal.title.innerHTML = `FeedBack for Service At ${t.created}`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Question ID</dt>
    <dd class="col-sm-8">${t.question_id}</dd>
    <dt class="col-sm-4">Service ID</dt>
    <dd class="col-sm-8">${t.service_id}</dd>
    <dt class="col-sm-4">User Id</dt>
    <dd class="col-sm-8">${t.user_id}</dd>
    <dt class="col-sm-4">Service Address</dt>
    <dd class="col-sm-8">${t.address}</dd>
    <dt class="col-sm-4">Service Pin Code</dt>
    <dd class="col-sm-8">${t.pincode}</dd>
    <dt class="col-sm-4">Time Slot</dt>
    <dd class="col-sm-8">${t.time_from} - ${t.time_to}</dd>
    <dt class="col-sm-4">Question</dt>
    <dd class="col-sm-8">${t.question}</dd>
    <dt class="col-sm-4">Answer</dt>
    <dd class="col-sm-12">
    ${
      t.status == "not-answered"
        ? `<textarea oninput="questionModalChangeHandler(this,'answer','${type}',${index});textResize(this)" style="width:100%"></textarea>`
        : t.answer
    }
    </dd>
    
  </dl>

    <button onclick="setServiceModal('${temp[0]}',${
    temp[1]
  })" class="btn btn-flat btn-primary">View Service Details</button>
    `;

  normalModal.register.onclick = () => {
    submitQuestionModal(type, index);
  };

  normalModal.switch.modal("show");
}

var questionModalChangeHandler = (element, input, type, index) => {
  textResize(element);
  data.questions[type][index]["temp_" + input] = element.value;
  normalModal.register.hidden = false;
};

function submitQuestionModal(type, index) {
  let e = data.questions[type][index];

  normalModal.register.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Submitting..`;
  normalModal.register.disabled = true;

  fetch("./modify_user_question", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify({
      question_id: e.question_id,
      user_uid: e.user_uid,
      answer: e.temp_answer,
    }),
  })
    .then((res) => res.json())
    .then(async (data) => {
      if (data.status == "success") {
        e.temp_answer = undefined;
        setQuestions();
        normalModal.register.className = "btn btn-flat btn-success";
        normalModal.register.innerHTML = `<i class="fas fa-check-circle"></i>&nbsp;Submitted Successfully`;
      } else {
        normalModal.register.className = "btn btn-flat btn-danger";
        normalModal.register.innerHTML = `<i class="fas fa-times-circle"></i>&nbsp;Submitted unsuccessfully`;
      }
      normalModal.register.disabled = false;
      normalModal.register.onclick = () => {
        normalModal.register.hidden = true;
        normalModal.switch.modal("hide");
      };
    });
}

async function setTickets() {
  data.tickets = util.classify(
    await (await fetch("./get_user_tickets")).json(),
    "status"
  );

  let names = Object.keys(data.tickets).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";

    let count = countUnread("user_read", "tickets", name);

    if (count > 0) {
      suffix = `<span class="m-1 position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary" id="tickets-${name}-count">${count}</span>`;
    }

    tabName += `<li class="nav-item">
              <a class="nav-link" id="tt-${name}" data-toggle="tab" href="#tc-${name}" role="tab" aria-controls="tc-${name}" aria-selected="true"><span class="position-relative">${ticket_status_map[name]}&nbsp;${suffix}</span></a>
            </li>`;
    tabContent += ` <div class="tab-pane fade show ${
      t1 == 0 ? "active" : ""
    } p-3" id="tc-${name}" role="tabpanel" aria-labelledby="tc-${name}-tab">
      <h4 class="text-center">${ticket_status_map[name]} Tickets</h4>
      <table class="table table-hover table-head-fixed"><thead><tr>
      <th>S-NO</th>
      <th>User Id</td>
      <th>Issue</th>
      <th>Last Modified</th>
      <th>Status</th>
    </tr></thead>`;
    let arr = data.tickets[name];
    arr.forEach((e, i) => {
      tabContent += `<tr class="read-${e.user_read}" style="cursor:pointer" onclick="setTicketModal('${name}',${i});markAsRead(this,'user_read', 'tickets','${name}',${i})">
        <td>${e.s_no}</td>              
        <td>${e.user_id}</td>              
        <td style="word-wrap:break-word;word-break:break-word">${e.issue}</td>              
        <td>${e.modified}</td>              
        <td>${e.status}</td>              
      <tr>`;
    });
    tabContent += `</table></div>`;
  });
  tabName += `</ul>`;
  tabContent += `</div>`;
  ticketsDom.content.innerHTML = tabName + tabContent;
}

function setTicketModal(type, index) {
  let t = data.tickets[type][index];

  normalModal.title.innerHTML = `Ticket Raised At ${t.created}`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Ticket ID</dt>
    <dd class="col-sm-8">${t.ticket_id}</dd>
    <dt class="col-sm-4">User Id</dt>
    <dd class="col-sm-8">${t.user_id}</dd>
    <dt class="col-sm-4">Issue</dt>
    <dd class="col-sm-8">${t.issue}</dd>
    <dt class="col-sm-4">Description</dt>
    <dd class="col-sm-8">${t.description}</dd>
    <dt class="col-sm-4">Raised At</dt>
    <dd class="col-sm-8">${t.created}</dd>
    <dt class="col-sm-4">Last Modified</dt>
    <dd class="col-sm-8">${t.modified}</dd>
    <dt class="col-sm-4">Status</dt>
    <dd class="col-sm-8">${ticket_status_map[t.status]}</dd>
    <dt class="col-sm-4">Solution</dt>
    <dd class="col-sm-4">${t.solution || "Not Resolved"}</dd>
  </dl>
    `;
  normalModal.switch.modal("show");
}

let notification_mapper = {
  pending: " newly registered",
  available: "available for booking",
  "not-available": "not available for booking",
  booked: "newly booked ",
  completed: "completed ",
  given: " newly completed ",
  "not-given": " not completed ",
  answered: " answered ",
  "not-answered": " not Answered",
  resolved: " newly resolved ",
  "not-resolved": " not Resolved",
};

function setNotification() {
  let str = "";
  let sum = 0;
  Object.keys(data.tickets).forEach((name) => {
    let count = countUnread("user_read", "tickets", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('tickets')" href="#" class="dropdown-item">
                      ${count} tickets ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  Object.keys(data.feedbacks).forEach((name) => {
    let count = countUnread("user_read", "feedbacks", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('feedbacks')" href="#" class="dropdown-item">
                      ${count} reviews ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  Object.keys(data.questions).forEach((name) => {
    let count = countUnread("user_read", "questions", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('questions')" href="#" class="dropdown-item">
                      ${count} questions ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  Object.keys(data.services).forEach((name) => {
    let count = countUnread("user_read", "services", name);
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
  await setTickets();
  await setFeedBacks();
  await setQuestions();
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
