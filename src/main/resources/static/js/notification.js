function countUnread(column, role, type) {
  return data[role][type].filter(function (item) {
    return item[column] === false;
  }).length;
}

function markAsRead(element, column, role, type, index) {
  let e = data[role][type][index];
  if (e[column] === false) {
    e[column] = true;
    if(element) element.className = "read-true";
    setNotification();
    // update fetch request
    if (role == "services") {
      fetch("./mark_read_service", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({
          column: column,
          service_id: e.service_id,
        }),
      });
    } else if (role == "cleaners") {
      fetch("./mark_read_cleaner", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({
          column: column,
          cleaner_id: e.cleaner_id,
        }),
      });
    } else if (role == "feedbacks") {
      fetch("./mark_read_feedback", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({
          column: column,
          service_id: e.service_id,
        }),
      });
    } else if (role == "questions") {
      fetch("./mark_read_question", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({
          column: column,
          question_id: e.question_id,
        }),
      });
    } else if (role == "tickets") {
      fetch("./mark_read_ticket", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({
          column: column,
          ticket_id: e.ticket_id,
        }),
      });
    }
    let temp = document.getElementById(role + "-" + type + "-count");
    if (temp) {
      let v = Number(temp.innerText);
      if (v > 1) {
        temp.innerText = v - 1;
      } else {
        temp.remove();
      }
    }
  }
}

function markAsReadProfile(column, role) {
  let e = data[role][0];
  if (e[column] === false) {
    e[column] = true;
    setNotification();
    // update fetch request
    if (role == "cleaners") {
      console.log("called");
      fetch("./mark_read_cleaner", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify({
          column: column,
          cleaner_id: e.cleaner_id,
        }),
      });
    }  
  }
}
