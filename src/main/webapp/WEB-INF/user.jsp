<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>RCS User</title>
    <link href="/img/favicon.ico?289349832" rel="icon" />

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="/plugins/fontawesome-free/css/all.min.css">

    <link rel="stylesheet" href="/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
    <link href="/plugins/toastr/toastr.min.css" rel="stylesheet" />

    <link rel="stylesheet" href="/plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="/plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
    <link rel="stylesheet" href="/plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
    <link rel="stylesheet" href="/plugins/datatables-fixedcolumns/css/fixedColumns.bootstrap4.min.css">

    <link rel="stylesheet" href="/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
    
    <link rel="stylesheet" href="/plugins/summernote/summernote-bs4.min.css">

    <link rel="stylesheet" href="/plugins/select2/css/select2.min.css">
    <link rel="stylesheet" href="/plugins/bootstrap-1/css/bootstrap.min.css">

    <link rel="stylesheet" href="/plugins/bootstrap-icon-picker/css/fontawesome-iconpicker.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/css/adminlte.min.css">
    <%-- <link rel="stylesheet" href="/css/admin.css"> --%>
    <style>
    .read-false td{
        font-weight: bold;
    }

    .read-true{
        
    }
    
        textarea{
        overflow: hidden;
    }

    .loading-screen{
        z-index: 5000;
        display: flex;
        align-items: center;
        justify-content: center;
        position: absolute;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        backdrop-filter: blur(4px);
    }

    .spin-center{
        left: 50%;
        margin-left: -4em;
    }



    </style>

</head>

<body class="hold-transition">

    <!-- layout-navbar-fixed layout-fixed -->
    <div class="wrapper">
        <div>
            <nav id="header" class="main-header bg-light navbar navbar-expand">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a id="menu-button" class="nav-link " data-widget="pushmenu" href="#" role="button">
                            <i class="fas fa-bars"></i>
                        </a>
                    </li>
                    <div id="content-header-left"></div>
                </ul>



                <!-- Messages Dropdown Menu -->
                <ul class="navbar-nav ml-auto">
                    <div id="content-header-right"></div>
                       <li class="nav-item dropdown" id="notification">
                        </li>
                    <li onclick="setMainContent('profile')" class="nav-item d-none d-sm-inline-block">
                        <a href="#" class="nav-link ">Profile</a>
                    </li>
                    <li onclick="setMainContent('tickets')" class="nav-item d-none d-sm-inline-block">
                        <a href="#" class="nav-link ">Tickets</a>
                    </li>
                    <li onclick="setMainContent('feedbacks')" class="nav-item d-none d-sm-inline-block">
                        <a href="#" class="nav-link ">Reviews</a>
                    </li>
                    <li onclick="setMainContent('questions')" class="nav-item d-none d-sm-inline-block">
                        <a href="#" class="nav-link ">Feedbacks</a>
                    </li>
                    <li onclick="setMainContent('services')" class="nav-item d-none d-sm-inline-block">
                        <a href="#" class="nav-link ">Services</a>
                    </li>
                    <li class="nav-item d-none d-sm-inline-block">
                        <a href="/log_out" class="nav-link ">Log out</a>
                    </li>
                </ul>
            </nav>
        </div>

        <aside id="sidebar" class="main-sidebar elevation-4 sidebar-dark-primary">
            <!-- Brand Logo -->
            <a href="#" class="brand-link">
                <span class="brand-text font-weight-light">Room Cleaning Service</span>
            </a>

            <!-- Sidebar -->
            <div class="sidebar">
                <!-- Sidebar Menu -->
                <nav class="mt-2">
                    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                        data-accordion="false">
                        <li id="tab-btn-contact" class="nav-item" onclick="setMainContentFromSideBar('profile')">
                            <a href="#" class="nav-link">
                                <p>
                                    Profile
                                </p>
                            </a>
                        </li>
                      <li id="tab-btn-contact" class="nav-item" onclick="setMainContentFromSideBar('tickets')">
                            <a href="#" class="nav-link">
                                <p>
                                    Tickets
                                </p>
                            </a>
                        </li>
                         <li id="tab-btn-contact" class="nav-item" onclick="setMainContentFromSideBar('feedbacks')">
                            <a href="#" class="nav-link">
                                <p>
                                    Reviews
                                </p>
                            </a>
                        </li>
                        <li id="tab-btn-contact" class="nav-item" onclick="setMainContentFromSideBar('questions')">
                            <a href="#" class="nav-link">
                                <p>
                                    Feedbacks
                                </p>
                            </a>
                        </li>
                        <li id="tab-btn-contact" class="nav-item" onclick="setMainContentFromSideBar('services')">
                            <a href="#" class="nav-link">
                                <p>
                                    Services
                                </p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="/log_out" class="nav-link">
                                <p>
                                    Log out
                                </p>
                            </a>
                        </li>
                    </ul>
                </nav>
                <!-- /.sidebar-menu -->
            </div>
            <!-- /.sidebar -->
        </aside>
        <!-- /.control-sidebar -->

        <!-- Content Wrapper. Contains page content -->
        <div id="content" class="content-wrapper">
            <!-- Content Header (Page header) -->
           
            <!-- /.content-header -->

            <!-- Main content -->
            <div class="content p-3">
                <div class="container-fluid">
                <div class="row w-100" id="profile-container" hidden>
                         <div class="col-12">
                            <div class="card" id="profile-content">
                                <div class="card-header">
                                    <h3 class="card-title"  id="profile-content-title">Profile</h3>
                                	<div class="float-right">
                                		<a href="/forget_password">Reset Password</a>
                                	</div>
                                </div>
                                <div class="card-body"  id="profile-content-body">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row w-100" id="tickets-container" hidden>
                         <div class="col-12">
                            <div class="card" id="tickets-content">
                                <div class="card-header">
                                    <h3 class="card-title"  id="tickets-content-title">Tickets</h3>
                                    <span class="float-right">
                                        <a role="button" class="btn btn-flat btn-primary" href="./user_help">Add New Ticket</a>
                                    </span>
                                </div>
                                <div class="card-body"  id="tickets-content-body">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row w-100" id="feedbacks-container" hidden>
                        <div class="col-12">
                            <div class="card" id="feedbacks-content">
                                <div class="card-header">
                                    <h3 class="card-title"  id="feedbacks-content-title">Reviews</h3>
                                </div>
                                <div class="card-body"  id="feedbacks-content-body">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row w-100" id="questions-container" hidden>
                        <div class="col-12">
                            <div class="card" id="questions-content">
                                <div class="card-header">
                                    <h3 class="card-title"  id="questions-content-title">FeedBacks</h3>
                                </div>
                                <div class="card-body"  id="questions-content-body">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row w-100" id="services-container" hidden>
                        <div class="col-12">
                            <div class="card" id="services-content">
                                <div class="card-header">
                                    <h3 class="card-title"  id="serives-content-title">Services</h3>
                                    <span class="float-right">
                                        <a role="button" class="btn btn-flat btn-primary" href="./service_booking">Book New Service</a>
                                    </span>
                                </div>
                                <div class="card-body"  id="services-content-body">
                                </div>
                            </div>
                        </div>
                    </div>
                 </div><!-- /.container-fluid -->
            </div>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <div id="main-loader" class="loading-screen">
            <div class="spinner-border text-primary spin-center" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>

    </div>

    <div class="modal fade" id="warning-modal" style="z-index:3000">
        <div class="modal-dialog modal-sm modal-dialog-centered">
          <div class="modal-content">
            <div class="modal-body">
              <p id='warning-modal-content'></p>
              <button type="button" class="btn btn-info btn-flat float-right" data-dismiss="modal">
                  Okay
              </button>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
      <!-- /.modal -->

    <div class="modal fade" id="normal-modal">
        <div class="modal-dialog modal-dialog-centered">
          <div class="modal-content">
              <div class="modal-header"><h5 class="w-100" id='normal-modal-title'>Header</h5></div>
            <div class="modal-body" id='normal-modal-content' style="max-height:70vh;overflow:auto">
              Hello
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-flat" data-dismiss="modal">
                    Close
                </button>
                <button id='normal-modal-register' type="button" class="btn btn-primary btn-flat" hidden>
                    Submit
                </button>
            </div>
          </div>
          <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
      <!-- /.modal -->


    <!-- REQUIRED SCRIPTS -->
    <!-- jQuery -->
    <script src="/plugins/jquery/jquery.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script src="/plugins/moment/moment.min.js"></script>

    <script src="/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
    

    <script src="/plugins/sweetalert2/sweetalert2.min.js"></script>

    <!-- for data tables -->
    <script src="/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="/plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
    <script src="/plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
    <script src="/plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
    <script src="/plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
    <script src="/plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
    <script src="/plugins/jszip/jszip.min.js"></script>
    <!-- <script src="/plugins/pdfmake/pdfmake.min.js"></script> -->
    <script src="/plugins/datatables-buttons/js/buttons.html5.min.js"></script>
    <script src="/plugins/datatables-buttons/js/buttons.print.min.js"></script>
    <script src="/plugins/datatables-fixedcolumns/js/fixedColumns.bootstrap4.min.js"></script>
    <script src="/plugins/datatables-buttons/js/buttons.colVis.min.js"></script>

    <script src="/plugins/summernote/summernote-bs4.min.js"></script>

    <script src="/plugins/select2/js/select2.full.min.js"></script>
    
    <script src="/plugins/bootstrap-icon-picker/js/fontawesome-iconpicker.min.js"></script>
    
    <!-- AdminLTE App -->
    <script src="/js/adminlte.min.js"></script>

    <script src="/js/notification.js"></script>
    <script src="/js/user.js"></script>
  </body>

</html>