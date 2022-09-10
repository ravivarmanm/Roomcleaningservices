var currentTab = "cleaners";
var curr_form_obj = -100;

const Toast = Swal.mixin({
  toast: true,
  position: "top-end",
  showConfirmButton: false,
  timer: 3000,
});

var data = {
  tickets: {},
  services: {},
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

var servicesDom = {
  container: document.getElementById("services-container"),
  content: document.getElementById("services-content-body"),
};

var cleanersDom = {
  container: document.getElementById("cleaners-container"),
  content: document.getElementById("cleaner-content-body"),
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

function setReportModal(){
  normalModal.register.hidden = true;
  
  normalModal.title.innerHTML = `Generating Report`;

  normalModal.content.innerHTML = `
  <form action="/generate_report" method="post" style="width:fit-content">
  <div class="input-group mb-3">
    <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="type" id="inlineRadio1" value="services" checked>
      <label class="form-check-label" for="inlineRadio1">Services Provided</label>
    </div>
    <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="type" id="inlineRadio2" value="feedback-review">
      <label class="form-check-label" for="inlineRadio2">FeedBack And Reviews</label>
    </div>
    <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="type" id="inlineRadio3" value="cleaners">
      <label class="form-check-label" for="inlineRadio3">Cleaners</label>
    </div>
  </div>
  <div class="input-group mb-3">
      <nav class="nav nav-pills nav-fill">
       <a onclick="setReportTimeRange('custom')" class="nav-link active nav-pills" href="#" data-toggle="tab" href="#report-timerange" role="tab" aria-controls="report-timerange">Custom</a>
        <a onclick="setReportTimeRange('today')" class="nav-link" href="#" data-toggle="tab" href="#report-timerange" role="tab" aria-controls="report-timerange">Today</a>
        <a onclick="setReportTimeRange('week')" class="nav-link" href="#" data-toggle="tab" href="#report-timerange" role="tab" aria-controls="report-timerange">This Week</a>
        <a onclick="setReportTimeRange('month')" class="nav-link" href="#" data-toggle="tab" href="#report-timerange" role="tab" aria-controls="report-timerange">This Month</a>
        <a onclick="setReportTimeRange('year')" class="nav-link" href="#" data-toggle="tab" href="#report-timerange" role="tab" aria-controls="report-timerange">This year</a>
      </nav>
      <div class="tab-pane fade show active w-100" id="report-timerange" role="tabpanel" aria-labelledby="report-timerange-tab">
          <div class="input-group mb-3 ">
          <label for="">From</label>
            <input required class="form-control w-100" type="datetime-local" id="report_time_from" name="report_time_from"  />
            <small id="time_from_help" class="form-text text-muted"></small>
          </div>
           <div class="input-group mb-3 ">
          	<label for="">To</label>
            <input required class="form-control w-100" type="datetime-local" id="report_time_to" name="report_time_to" />
            <small id="time_to_help" class="form-text text-muted"></small>
          </div>
      </div>
  </div>
   	 <label for="">Output Format</label>
<div class="input-group mb-3">
    <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="format" id="inlineRadio4" value="excel" checked>
      <label class="form-check-label" for="inlineRadio4">EXCEL</label>
    </div>
    <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="format" id="inlineRadio5" value="pdf">
      <label class="form-check-label" for="inlineRadio5">PDF</label>
    </div>
  </div>
  <div class="row">
    <div class="col-8">
    </div>
    <div class="col-4">
      <button type="submit" class="btn btn-primary btn-block">Generate</button>
    </div>
  </div>
</form>
    `;

  normalModal.switch.modal("show");
}

function setReportTimeRange(variant){
  let tf = document.getElementById("report_time_from");
  let tt = document.getElementById("report_time_to");
  let date = new Date();
  if(variant == "today"){
    tf.value = date.toJSON().slice(0,11)+"00:00";
    tt.value = date.toJSON().slice(0,11)+"23:59";
    tf.readOnly = true;
    tt.readOnly = true;
  }else if(variant == "week"){ 
    var diff = date.getDate() - date.getDay();
    var temp = new Date(date.setDate(diff));
    tf.value = temp.toJSON().slice(0,11)+"00:00";
    temp.setDate(temp.getDate()+6)
    tt.value = temp.toJSON().slice(0,11)+"23:59";
    tf.readOnly = true;
    tt.readOnly = true;
  }else if(variant == "month"){ 
    let firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    let lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    firstDay.setDate(firstDay.getDate()+1)
    lastDay.setDate(lastDay.getDate()+1)
    tf.value = firstDay.toJSON().slice(0,11)+"00:00";
    tt.value = lastDay.toJSON().slice(0,11)+"23:59";
    tf.readOnly = true;
    tt.readOnly = true;
  }else if(variant == "year"){ 
    let currentYear = new Date().getFullYear();
    let firstDay = new Date(currentYear, 0, 1);
    let lastDay = new Date(currentYear, 11, 31);
    
    firstDay.setDate(firstDay.getDate()+1)
    lastDay.setDate(lastDay.getDate()+1)

    tf.value = firstDay.toJSON().slice(0,11)+"00:00";
    tt.value = lastDay.toJSON().slice(0,11)+"23:59";
    tf.readOnly = true;
    tt.readOnly = true;
  }else if(variant == "custom"){ 
      tf.readOnly = false;
      tt.readOnly = false;
  }
}

function setMainContent(value, others = {}) {
  mainLoader.hidden = false;
  servicesDom.container.hidden = true;
  ticketsDom.container.hidden = true;
  cleanersDom.container.hidden = true;
  feedbacksDom.container.hidden = true;
  questionsDom.container.hidden = true;
  if (value == "services") {
    servicesDom.container.hidden = false;
  } else if (value == "tickets") {
    ticketsDom.container.hidden = false;
  } else if (value == "cleaners") {
    cleanersDom.container.hidden = false;
  } else if (value == "feedbacks") {
    feedbacksDom.container.hidden = false;
  } else if (value == "questions") {
    questionsDom.container.hidden = false;
  }
  mainLoader.hidden = true;
}

let cleaner_profile_map = {
  pending: "Pending",
  approved: "Approved",
  denied: "Denied",
};

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

async function setCleaners() {
  data.cleaners = await util.classify(
    await (await fetch("./get_cleaners")).json(),
    "profile_status"
  );
  console.log(data);
  let names = Object.keys(data.cleaners).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";

    let count = countUnread("admin_read", "cleaners", name);
    if (count > 0) {
      suffix = `<span class="m-1 position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary" id="cleaners-${name}-count">${count}</span>`;
    }

    tabName += `<li class="nav-item">
              <a class="nav-link position-relative" id="ct-${name}" data-toggle="tab" href="#cc-${name}" role="tab" aria-controls="cc-${name}" aria-selected="true"><span class="position-relative">${cleaner_profile_map[name]}&nbsp;${suffix}</span></a>
            </li>`;
    tabContent += ` <div class="tab-pane fade show ${
      t1 == 0 ? "active" : ""
    }  p-3" id="cc-${name}" role="tabpanel" aria-labelledby="cc-${name}-tab">
      <h4 class="text-center">Cleaners under ${name} state</h4>
      <table class="table table-hover table-head-fixed text-nowrap"><thead><tr>
      <th>S-NO</th>
      <th>Cleaner Id</th>
      <th>Name</td>
      <th>Gender</th>
      <th>Phone</th>
      <th>Last Modified</th>
    </tr></thead>`;
    let arr = data.cleaners[name];
    arr.forEach((e, i) => {
      tabContent += `<tr class="read-${
        e.admin_read
      } style="cursor:pointer" onclick="setCleanerModal('${name}',${i});markAsRead(this,'admin_read', 'cleaners','${name}',${i})">
        <td>${e.s_no}</td>              
        <td>${e.cleaner_id}</td>              
        <td>${e.first + " " + e.last}</td>              
        <td>${e.gender}</td>              
        <td>${e.phone}</td>              
        <td>${e.modified}</td>              
      <tr>`;
    });
    tabContent += `</table></div>`;
  });
  tabName += `</ul>`;
  tabContent += `</div>`;
  cleanersDom.content.innerHTML = tabName + tabContent;
}

function setCleanerModal(type, index) {
  let t = data.cleaners[type][index];

  normalModal.register.innerHTML = "Modify Status";
  normalModal.register.className = "btn btn-flat btn-primary";
  normalModal.register.disabled = false;
  normalModal.register.hidden = true;

  normalModal.title.innerHTML = `${t.cleaner_id} Details`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Cleaner ID</dt>
    <dd class="col-sm-8">${t.cleaner_id}</dd>
    <dt class="col-sm-4">Full Name</dt>
    <dd class="col-sm-8">${t.first + ", " + t.last}</dd>
    <dt class="col-sm-4">DOB</dt>
    <dd class="col-sm-8">${t.dob}</dd>
    <dt class="col-sm-4">Gender</dt>
    <dd class="col-sm-8">${t.gender}</dd>
    <dt class="col-sm-4">Phone</dt>
    <dd class="col-sm-8">${t.phone}</dd>
    <dt class="col-sm-4">Registered At</dt>
    <dd class="col-sm-8">${t.created}</dd>
    <dt class="col-sm-4">Modified At</dt>
    <dd class="col-sm-8">${t.modified}</dd>
    <dt class="col-sm-4">Profile Status</dt>
    <dd class="col-sm-8">
      <select class="form-control" 
      onchange="cleanerModalChangeHandler(this,'profile_status','${type}',${index})">
        <option ${
          t.profile_status == "pending" ? "selected" : ""
        } value="pending">Pending</option>
        <option ${
          t.profile_status == "denied" ? "selected" : ""
        } value="denied">Denied</option>
        <option ${
          t.profile_status == "approved" ? "selected" : ""
        } value="approved">Approved</option>
      </select>
    </dd>
  </dl>
  <div>
      <a class="btn btn-primary btn-flat m-2" role="button" target="_blank" href="./downloadFile/${
        t.uid
      }resume">View Resume</a>
      <a class="btn btn-primary btn-flat m-2" role="button" target="_blank" href="./downloadFile/${
        t.uid
      }health">View health certificate</a>
      <a class="btn btn-primary btn-flat m-2" role="button" target="_blank" href="./downloadFile/${
        t.uid
      }education">View Educational certificate</a>
  </div>
    `;
  normalModal.register.onclick = () => {
    submitCleanerModal(type, index);
  };
  normalModal.switch.modal("show");
}

var cleanerModalChangeHandler = (element, input, type, index) => {
  data.cleaners[type][index]["temp_" + input] = element.value;
  normalModal.register.hidden = false;
};

function submitCleanerModal(type, index) {
  let e = data.cleaners[type][index];

  if (e.profile_status !== e.temp_profile_status) {
    normalModal.register.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Updating..`;
    normalModal.register.disabled = true;

    fetch("./modify_cleaner", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        cleaner_id: e.cleaner_id,
        profile_status: e.temp_profile_status || e.profile_status,
      }),
    })
      .then((res) => res.json())
      .then(async (data) => {
        if (data.status == "success") {
          e.profile_status = e.temp_profile_status || e.profile_status;
          e.temp_profile_status = undefined;
          setCleaners();
          normalModal.register.className = "btn btn-flat btn-success";
          normalModal.register.innerHTML = `<i class="fas fa-check-circle"></i>&nbsp;Modified Successfully`;
        } else {
          normalModal.register.className = "btn btn-flat btn-danger";
          normalModal.register.innerHTML = `<i class="fas fa-times-circle"></i>&nbsp;Modified unsuccessfully`;
        }
        normalModal.register.disabled = false;
        normalModal.register.onclick = () => {
          normalModal.register.hidden = true;
          normalModal.switch.modal("hide");
        };
      });
  }
}

let ticket_status_map = {
  resolved: "Resolved",
  "not-resolved": "Not Resolved",
};

async function setTickets() {
  data.tickets = util.classify(
    await (await fetch("./get_tickets")).json(),
    "status"
  );

  let names = Object.keys(data.tickets).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";

    let count = countUnread("admin_read", "tickets", name);

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
      tabContent += `<tr class="read-${e.admin_read}" style="cursor:pointer" onclick="setTicketModal('${name}',${i});markAsRead(this,'admin_read', 'tickets','${name}',${i})">
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

  if (t.status == "resolved") normalModal.register.innerHTML = "Resolve Again";
  else normalModal.register.innerHTML = "Resolve";

  normalModal.register.disabled = false;
  normalModal.register.className = "btn btn-flat btn-primary";

  normalModal.title.innerHTML = `Ticket from ${t.user_id}`;

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
    <dd class="col-12">
      <textarea data-load="textResize(this)" style="width:100%" oninput="ticketModalChangeHandler(this,'solution','${type}',${index})">${
    t.solution || "Not Resolved"
  }</textarea>
    </dd>
    
  </dl>
    `;
  normalModal.register.onclick = () => {
    submitTicketModal(type, index);
  };
  normalModal.switch.modal("show");
}

var ticketModalChangeHandler = (element, input, type, index) => {
  textResize(element);
  data.tickets[type][index]["temp_" + input] = element.value;
  normalModal.register.hidden = false;
};

function submitTicketModal(type, index) {
  let e = data.tickets[type][index];

  if (e.solution !== e.temp_solution) {
    normalModal.register.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Resolving..`;
    normalModal.register.disabled = true;

    fetch("./modify_ticket", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        ticket_id: e.ticket_id,
        solution: e.temp_solution || e.solution,
      }),
    })
      .then((res) => res.json())
      .then(async (data) => {
        if (data.status == "success") {
          e.solution = e.temp_solution || e.solution;
          e.temp_solution = undefined;
          setTickets();
          normalModal.register.className = "btn btn-flat btn-success";
          normalModal.register.innerHTML = `<i class="fas fa-check-circle"></i>&nbsp;Resolved Successfully`;
        } else {
          normalModal.register.className = "btn btn-flat btn-danger";
          normalModal.register.innerHTML = `<i class="fas fa-times-circle"></i>&nbsp;Resolving Failed`;
        }
        normalModal.register.disabled = false;
        normalModal.register.onclick = () => {
          normalModal.register.hidden = true;
          normalModal.switch.modal("hide");
        };
      });
  }
}

let service_mapper = {
  pending: "Requesting Availability",
  available: "Available",
  "not-available": "Not Available",
  booked: "Booked",
  completed: "Completed",
};

async function setServices() {
  data.services = util.classify(
    await (await fetch("./get_services")).json(),
    "status"
  );

  let names = Object.keys(data.services).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";
    let count = countUnread("admin_read", "services", name);
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
      <table id="services-${name}" class="display table table-hover table-head-fixed text-nowrap"><thead><tr>
      <th>S-NO</th>
      <th>User Id</td>
      ${
        name != "pending" && name != "not-available"
          ? "<th>Cleaner Id</th>"
          : ""
      }
      <th>Address</th>
      <th>Pin Code</th>
      <th>From</th>
      <th>To</th>
    </tr></thead><tbody>`;
    let arr = data.services[name];
    arr.forEach((e, i) => {
      tabContent += `<tr class="read-${
        e.admin_read
      } style="cursor:pointer" onclick="setServiceModal('${name}',${i});markAsRead(this,'admin_read', 'services','${name}',${i})">
        <td>${e.s_no}</td>              
        <td>${e.user_id}</td>
        ${
          name != "pending" && name != "not-available"
            ? "<td>" + e.cleaner_id + "</td>"
            : ""
        }          
        <td>${e.address}</td>              
        <td>${e.pincode}</td>              
        <td>${e.time_from}</td>              
        <td>${e.time_to}</td>              
      <tr>`;
    });
    tabContent += `</tbody></table></div>`;
  });

  tabName += `</ul>`;
  tabContent += `</div>`;
  servicesDom.content.innerHTML = tabName + tabContent;

  setTimeout(
    () =>
      names.forEach(async (name) => {
        console.log($("#services-" + name));
        await $("#services-" + name).DataTable({
          lengthChange: false,
          autoWidth: true,
          scrollX: true,
          select: true,
          scrollCollapse: true,
        });
      }),
    100
  );
}

let questions_mapper = {
  answered: "Answered",
  "not-answered": "Not Answered",
};

async function setQuestions() {
  data.questions = util.classify(
    await (await fetch("./get_questions")).json(),
    "status"
  );

  let names = Object.keys(data.questions).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";
    let count = countUnread("admin_read", "questions", name);
    if (count > 0) {
      suffix = `<span class="m-1 position-absolute top-0 start-100 translate-middle badge rounded-pill bg-primary" id="questions-${name}-count">${count}</span>`;
    }

    tabName += `<li class="nav-item">
              <a class="nav-link" id="tt-${name}" data-toggle="tab" href="#tc-${name}" role="tab" aria-controls="tc-${name}" aria-selected="true"><span class="position-relative">${questions_mapper[name]}&nbsp;${suffix}</span></a>
            </li>`;
    tabContent += ` <div class="tab-pane fade show ${
      t1 == 0 ? "active" : ""
    } p-3" id="tc-${name}" role="tabpanel" aria-labelledby="tc-${name}-tab">
      <h4 class="text-center">${questions_mapper[name]} feedbacks</h4>
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
      tabContent += `<tr class="read-${e.admin_read}" style="cursor:pointer" onclick="setQuestionModal('${name}',${i});markAsRead(this,'admin_read', 'questions','${name}',${i})">
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
  let temp1 = findTypeAndIndex("cleaners", "uid", t.cleaner_uid);

  normalModal.register.hidden = true;
  normalModal.title.innerHTML = `Feedback Question for ${t.user_id}`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Question ID</dt>
    <dd class="col-sm-8">${t.question_id}</dd>
    <dt class="col-sm-4">Service ID</dt>
    <dd class="col-sm-8">${t.service_id}</dd>
    <dt class="col-sm-4">Question</dt>
    <dd class="col-sm-8">${t.question}</dd>
    <dt class="col-sm-4">Answer</dt>
    <dd class="col-sm-8">${t.answer}</dd>
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

    <button onclick="setCleanerModal('${temp1[0]}',${temp1[1]})" class="btn btn-flat btn-primary">View Cleaner Details</button>
    `;
  normalModal.switch.modal("show");
}

function setAddQuestionModal(type, index) {
  let t = data.services[type][index];

  normalModal.register.innerHTML = "Send Feedback";
  normalModal.register.className = "btn btn-flat btn-primary";
  normalModal.register.disabled = false;

  normalModal.register.hidden = true;
  normalModal.title.innerHTML = `Asking FeedBack to ${t.user_id}`;

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
    <dt class="col-sm-4">Question</dt>
    <dd class="col-12">
      <textarea data-load="textResize(this)" style="width:100%" oninput="questionModalChangeHandler(this,'question','${type}',${index})"></textarea>
    </dd>
  </dl>
    `;

  normalModal.register.onclick = () => {
    if (t.temp_question && t.temp_question.trim().length > 5) {
      submitQuestionModal(type, index);
    } else {
      alert("Question must not be blank and minimum 5 characters");
    }
  };
  normalModal.switch.modal("show");
}

var questionModalChangeHandler = (element, input, type, index) => {
  textResize(element);
  data.services[type][index]["temp_" + input] = element.value;
  normalModal.register.hidden = false;
};

function submitQuestionModal(type, index) {
  let e = data.services[type][index];

  normalModal.register.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Sending..`;
  normalModal.register.disabled = true;

  fetch("./add_question", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify({
      service_id: e.service_id,
      user_uid: e.user_uid,
      cleaner_uid: e.cleaner_uid,
      question: e.temp_question,
    }),
  })
    .then((res) => res.json())
    .then(async (data) => {
      if (data.status == "success") {
        e.temp_question = undefined;
        setQuestions();
        normalModal.register.className = "btn btn-flat btn-success";
        normalModal.register.innerHTML = `<i class="fas fa-check-circle"></i>&nbsp;Feedback Sent`;
      } else {
        normalModal.register.className = "btn btn-flat btn-danger";
        normalModal.register.innerHTML = `<i class="fas fa-times-circle"></i>&nbsp;Feedback Not Sent`;
      }
      normalModal.register.disabled = false;
      normalModal.register.onclick = () => {
        normalModal.register.hidden = true;
        normalModal.switch.modal("hide");
      };
    });
}

let feedbacks_mapper = {
  given: "Given",
  "not-given": "Not Given",
};

async function setFeedBacks() {
  data.feedbacks = util.classify(
    await (await fetch("./get_feedbacks")).json(),
    "status"
  );

  let names = Object.keys(data.feedbacks).sort();
  let tabName = `<ul class="nav nav-tabs w-100" id="myTab" role="tablist">`;
  let tabContent = `<div class="tab-content table-responsive">`;
  names.forEach((name, t1) => {
    let suffix = "";

    let count = countUnread("admin_read", "feedbacks", name);
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
      tabContent += `<tr class="read-${e.admin_read} style="cursor:pointer" onclick="setFeedBackModal('${name}',${i});markAsRead(this,'admin_read', 'feedbacks','${name}',${i})">
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

function setFeedBackModal(type, index) {
  let t = data.feedbacks[type][index];

  let temp = findTypeAndIndex("services", "service_id", t.service_id);
  let temp1 = findTypeAndIndex("cleaners", "uid", t.cleaner_uid);

  normalModal.register.hidden = true;
  normalModal.title.innerHTML = `Review From ${t.user_id}`;

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

   <button onclick="setAddQuestionModal('${temp[0]}',${temp[1]})" class="btn btn-flat btn-primary">Ask a Feedback</button>

    <button onclick="setServiceModal('${temp[0]}',${temp[1]})" class="btn btn-flat btn-primary">View Service Details</button>

    <button onclick="setCleanerModal('${temp1[0]}',${temp1[1]})" class="btn btn-flat btn-primary">View Cleaner Details</button>
    `;
  normalModal.switch.modal("show");
}

function setServiceModal(type, index) {
  let t = data.services[type][index];

  normalModal.register.innerHTML = "Update Status";
  normalModal.register.className = "btn btn-flat btn-primary";
  normalModal.register.disabled = false;
  normalModal.register.hidden = true;

  let temp = findTypeAndIndex("feedbacks", "service_id", t.service_id);

  normalModal.title.innerHTML = `Service Details - Registered by ${t.user_id}`;

  normalModal.content.innerHTML = `
  <dl class="row">
    <dt class="col-sm-4">S.No</dt>
    <dd class="col-sm-8">${t.s_no}</dd>
    <dt class="col-sm-4">Service ID</dt>
    <dd class="col-sm-8">${t.service_id}</dd>
    <dt class="col-sm-4">User Id</dt>
    <dd class="col-sm-8">${t.user_id}</dd>
    <dt class="col-sm-4">Cleaner Id</dt>
    <dd class="col-sm-8">${t.cleaner_id || "Not Alloted"}</dd>
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
    <dd class="col-sm-8">${service_mapper[t.status]}</dd>
    ${
      t.status != "booked" || t.status != "completed"
        ? `<dt class="col-sm-4">Status</dt>
      <dd class="col-sm-8">
        <select class="form-control" 
        onchange="serviceModalChangeHandler(this,'status','${type}',${index})">
          <option ${
            t.status == "pending" ? "selected" : ""
          } value="pending">Pending</option>
          <option ${
            t.status == "available" ? "selected" : ""
          } value="available">Available</option>
          <option ${
            t.status == "not-available" ? "selected" : ""
          } value="not-available">Not Available</option>
        </select>
      </dd>
      <dt class="col-sm-4">Available Cleaners</dt>
      <dd class="col-sm-8">
      <select class="form-control" 
        onchange="serviceModalChangeHandler(this,'cleaner_uid','${type}',${index})">
          ${setCleanerOptions(t.cleaner_uid)}
        </select>
      </dd>
      `
        : ""
    }
  </dl>
    `;

  if (t.status == "completed") {
    normalModal.content.innerHTML += `<button onclick="setFeedBackModal('${temp[0]}',${temp[1]})" class="btn btn-flat btn-primary">View FeedBack Details</button>
    
    <button onclick="setAddQuestionModal('${type}',${index})" class="btn btn-flat btn-primary">Ask a FeedBack</button>

    `;
  }

  normalModal.register.onclick = () => {
    submitServiceModal(type, index);
  };
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

function setCleanerOptions(cleaner_uid) {
  str = "<option value=null>Not Selected</option>";
  (data.cleaners["approved"] || []).forEach((e, i) => {
    if (e.uid == cleaner_uid) {
      str += `<option selected value="${e.uid}">${e.cleaner_id}</option>`;
    } else str += `<option value="${e.uid}">${e.cleaner_id}</option>`;
  });

  return str;
}

function submitServiceModal(type, index) {
  let e = data.services[type][index];

  // if (e.temp_cleaner_uid == "") e.temp_cleaner_uid = undefined;

  if (e.status !== e.temp_status || e.cleaner_uid !== e.temp_cleaner_uid) {
    normalModal.register.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Updating..`;
    normalModal.register.disabled = true;

    fetch("./modify_service", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify({
        service_id: e.service_id,
        status: e.temp_status || e.status,
        cleaner_uid: e.temp_cleaner_uid || e.cleaner_uid,
      }),
    })
      .then((res) => res.json())
      .then(async (data) => {
        if (data.status == "success") {
          e.status = e.temp_status || e.status;
          e.cleaner_uid = e.temp_cleaner_uid || e.cleaner_uid;
          e.temp_status = undefined;
          e.temp_cleaner_uid = undefined;
          setServices();
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
  given: " newly completed ",
  "not-given": " not completed ",
  answered: " answered ",
  "not-answered": " not Answered",
  resolved: " newly resolved ",
  "not-resolved": " not Resolved",
  pending: " are asking approval",
  approved: " are newly approved ",
  denied: " are newly rejected",
};

function setNotification() {
  let str = "";
  let sum = 0;

  Object.keys(data.cleaners).forEach((name) => {
    let count = countUnread("admin_read", "cleaners", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('cleaners')" href="#" class="dropdown-item">
                      ${count} cleaners ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  Object.keys(data.tickets).forEach((name) => {
    let count = countUnread("admin_read", "tickets", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('tickets')" href="#" class="dropdown-item">
                      ${count} tickets ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  Object.keys(data.feedbacks).forEach((name) => {
    let count = countUnread("admin_read", "feedbacks", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('feedbacks')" href="#" class="dropdown-item">
                      ${count} reviews ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  Object.keys(data.questions).forEach((name) => {
    let count = countUnread("admin_read", "questions", name);
    if (count > 0) {
      str += ` <div class="dropdown-divider"></div>
                  <a onclick="setMainContent('questions')" href="#" class="dropdown-item">
                      ${count} questions ${notification_mapper[name]}
                  </a>`;
    }
    sum += count;
  });

  Object.keys(data.services).forEach((name) => {
    let count = countUnread("admin_read", "services", name);
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
  await setCleaners();
  await setServices();
  await setTickets();
  await setFeedBacks();
  await setQuestions();
  await setNotification();
  setMainContent(currentTab);
  handleResize();

  // setInterval(() => {
  //   refresh();
  // }, 30000);
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
