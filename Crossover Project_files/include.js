<!DOCTYPE html>
<html class="js no-touch svg" ng-app="bandcampApp">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Crossover</title>
  <meta name="description" content="">
  <meta name="viewport" content="width=device-width">
  <base href="/x/">
  <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->

  <script defer src="scripts/thirdparty/39c74866.new-relic.js"></script>

  <link rel="stylesheet" href="styles/0f87dd59.main.css">
</head>

<body ng-controller="HeaderCtrl" ng-cloak ng-class="{isAvailableJobs: isAvailableJobs}">
<!--[if lt IE 7]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser.
  Please
  <a href="http://browsehappy.com/">upgrade your browser</a>
  to improve your experience.
</p><![endif]-->

<!--[if lt IE 9]>
<script src="bower_components/es5-shim/es5-shim.js"></script>
<script src="bower_components/json3/lib/json3.min.js"></script>
<![endif]-->

<script src="//apis.google.com/js/platform.js" defer="defer"></script>

<!-- Add your site or application content here -->
<header ng-class="{'dark': isDarkHeader, 'logged': isLogged && !isHome(path)}">
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header pull-left">
        <a class="navbar-brand" ng-href="https://www.crossover.com">
          <div class="logo"></div>
        </a>
      </div>

      <div class="navbar-header pull-right">
        <button type="button" class="navbar-toggle collapsed top17" ng-click="isCollapsed=!isCollapsed">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
      </div>

      <div ng-class="{'navbar-dark': isHome(path)}" class="collapse navbar-collapse" uib-collapse="isCollapsed">
        <ul ng-if="isLogged && user.avatarTypes.length !== 1 && user.avatarTypes.indexOf('ADMIN') !== -1 && !isAvailableJobs" class="nav navbar-nav resources-tabs">
          <li>
            <a ng-href="contact-us">
              <span><i class="cIcon ciSupport"></i>Support</span>
            </a>
          </li>
        </ul>
        <ul ng-if="!isLogged && !isAvailableJobs" class="nav navbar-nav navbar-right user-tabs">
          <li><a ng-href="login">Login</a></li>
          <!--<li><a ng-href="marketplace/find-work">Find a Job</a></li>-->
          <li><a ng-href="marketplace/available-jobs">Available Jobs</a></li>
        </ul>
        <ul ng-if="isAvailableJobs" class="nav navbar-nav navbar-right user-tabs">
          <li><a href="marketplace/available-jobs">Jobs</a></li>
          <li class="act">
            <a href="https://www.crossover.com/about-us/">About</a>
            <div class="aj-sub-menu">
              <a href="https://www.crossover.com/about-us/">About Us</a>
              <a href="https://www.crossover.com/social-mission/">Mission</a>
              <a href="https://www.crossover.com/talent/">Careers</a>
            </div>
          </li>
          <li><a href="https://www.crossover.com/blog/">Blog</a></li>
          <li><a href="https://www.crossover.com/contact-crossover/">Contact</a></li>
          <li><a href="login">Login</a></li>
        </ul>
        <ul ng-if="isLogged && !isAvailableJobs" class="nav navbar-nav navbar-right user-tabs">
          <li ng-repeat="t in [] | numRange:1:navList.length track by $index" ng-class="{'active': $index === currentTabIndex, 'open' : openTabIndex === $index }" ng-mouseenter="openTabIndex = $index; closeDropdowns();" ng-mouseleave="openTabIndex = -1">
            <a ng-href="{{navList[$index].href}}" ng-bind="navList[$index].title"></a>
            <ul ng-if="navList[$index].subList">
              <li ng-repeat="s in [] | numRange:1:navList[$index].subList.length track by $index">
                <a ng-href="{{navList[$parent.$index].subList[$index].href}}" ng-bind="navList[$parent.$index].subList[$index].title" ng-class="{active: $index === aVal(currentSubTabIndex) && $parent.$index === currentTabIndex }"></a>
          </li>
            </ul>
          </li>
          <li onselectstart="return false;" style="padding-top: 2px" ng-class="{'open': ui.notifications}" class="dropdownMenu">
            <div class="dropdown-toggle clickable notification-icon" ng-class="{'active': notifications.length > 0}" ng-click="ui.notifications = !ui.notifications; ui.settings = false;">
              <span class="cIcon ciBell"></span>
              <span class="round">{{notifications.length}}</span>
            </div>
            <ul class="dropdown-menu notifications">
              <li>
                <h5>
                  Notifications
                </h5>
                <div class="notifications-container">
                  <div class="notification" ng-repeat="n in notifications | orderBy: 'updated': true" ng-click="showNotification($event, n)" ng-if="$index < 3">
                    <div class="sender-icon"></div>
                    <div>
                      <p>Crossover</p>
                      <p class="time" am-time-ago="n.updated"></p>
                      <p class="message ellipsis" ng-bind-html="n.message"></p>
                    </div>
                  </div>
                  <div class="text-center notification" ng-hide="notifications.length > 0">
                    You have no pending notifications.
                  </div>
                  <div class="text-center" ng-show="notifications.length > 0">
                    <hr>
                    <a ng-href="notifications">
                      See all notifications
                    </a>
                  </div>
                </div>
              </li><li>
            </li></ul>
          </li>
          <li class="settings-menu dropdownMenu" ng-class="{'open': ui.settings}">
            <a class="dropdown-toggle" ng-click=" ui.settings = !ui.settings; ui.notifications = false;">
              <div class="menu-icon">&#9776;</div>
            </a>
            <ul>
              <li>
                <a ng-href="{{settingsUrl}}" ng-click="ui.settings = !ui.settings;">
                  <i class="cIcon ciGear right5"></i>&nbsp;&nbsp;Settings</a>
              </li>
              <li ng-click="rootScope.logout()">
                <a ng-href=""><i class="cIcon ciExit right5"></i>&nbsp;&nbsp;Sign Out</a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div class="subheader" ng-show="isSubtitleBarVisible && navList[aVal(currentTabIndex)].level === 3">
    <div class="container-fluid">
      <!-- Show the header name if we do not has sub menu-->
      <div compile="subtitle" custom-scope="innerScope" class="subtitle" ng-if="!navList[aVal(currentTabIndex)].subList[aVal(currentSubTabIndex)].subList"></div>
      <ul class="sublist text-center" ng-if="navList[aVal(currentTabIndex)].level !== 3">
        <li ng-repeat="s in navList[aVal(currentTabIndex)].subList" ng-class="{active: $index === aVal(currentSubTabIndex)}">
          <a ng-href="{{s.href}}" style="color: white" ng-bind="s.title"></a>
        </li>
      </ul>
      <ul class="sublist text-center" ng-if="navList[aVal(currentTabIndex)].level === 3 && aVal(currentSubTabIndex) === $index" ng-repeat="s in navList[aVal(currentTabIndex)].subList track by $index">
        <li ng-repeat="t in s.subList track by $index" ng-class="{'active': $index === aVal(currentSubTabIndexLevel3)}">
          <a ng-href="{{t.href}}" style="color: white" ng-bind="t.title"></a>
        </li>
      </ul>
    </div>
  </div>
</header>

<!--flash message-->
<div class="container-fluid" ng-show="flashMsg && flashMsg.message" ng-cloak>
  <div class="alert top30" ng-class="{'alert-success': flashMsg.type === 'success', 'alert-danger': flashMsg.type === 'error', 'alert-info': flashMsg.type==='info'}">
    {{flashMsg.message}}
  </div>
</div>

<!-- ACTUAL TEMLATE CONTENT -->
<div class="content" ng-view ng-style="showFooter && {'margin-bottom': '90px'}"></div>

<footer class="clearfix" ng-if="showFooter">
  <nav class="container-fluid navbar navbar-default">
    <ul class="nav pull-left nopadding resources-tabs">
      <li>
        <a ng-href="home" class="cIcon ciLogo nodecoration font48 vcenter"></a>
      </li>
      <li ng-if="isLogged" class="hidden-xs">
        <a ng-href="contact-us">
          <span><i class="cIcon ciSupport"></i>Support</span>
        </a>
      </li>
      <li ng-if="user && (user.avatarTypes.indexOf('MANAGER') > -1 || user.avatarTypes.indexOf('CANDIDATE') > -1)" class="hidden-xs">
        <a ng-href="resources">
          <span><i class="cIcon ciResources"></i>Resources</span>
        </a>
      </li>
    </ul>
    <ul class="nav pull-right copyright">
      <li>
        &copy; 2016 Crossover
      </li>
      <li class="privacy-policy" ng-hide="path.indexOf('login') > -1">
        <a ng-href="documents/privacy">Privacy policy</a>
      </li>
    </ul>
  </nav>
</footer>

<script type="text/ng-template" id="bad-timezone-modal.tpl.html">
  <div class="modal-body text-center">
    <div class="">
      <p>
        It looks like you are in
        <span ng-if="ipData.city">
          {{ipData.city}}, {{ipData.country.name}}.
        </span>
        <span ng-if="!ipData.city">
          GMT{{currentTimeZone}}.
        </span>
      </p>
      <p>
        Do you want to change your timezone
        <span ng-show="ipData.timeZone">
          to {{ipData.timeZone.name}}
          (UTC{{ipData.timeZone.hourlyOffset}})
        </span>
        <span ng-hide="ipData.timeZone && !ipData.country">
          from UTC{{user.location.timeZone.hourlyOffset}}?
        </span>
        <span ng-hide="ipData.timeZone && ipData.country">
          to UTC{{currentTimeZone}}?
        </span>
      </p>
    </div>
    <div class="top20">
      <span class="btn btn-primary" ng-click="change()">Change
      </span>
      <span class="btn btn-default" ng-click="cancel()">Dismiss
      </span>
    </div>
  </div>
</script>

<script defer src="scripts/f6fc0d8e.cdns.js"></script>

<script defer src="scripts/4c0fcb6a.plugins.js"></script>

<script type="text/javascript">
  var io_install_flash = true;
  var io_install_stm = false;
  var io_exclude_stm = 12;
  var io_enable_rip = true;
</script>
<script async src="//ci-mpsnare.iovation.com/snare.js"></script>
<script async src="iojs/latest/static_wdp.js"></script>
<script async src="/iojs/4.1.1/dyn_wdp.js"></script>

<script defer src="scripts/ae648a8f.modules.js"></script>

<script defer src="scripts/b3fe1cf9.scripts.js"></script>

<script defer src="scripts/da9cddaa.templates.js"></script>

<script defer src="scripts/78b64538._config.js"></script>

<script async src="//maps.googleapis.com/maps/api/js?libraries=places"></script>
<script async type="text/javascript" src="//www.googleadservices.com/pagead/conversion_async.js" charset="utf-8"></script>
<script async src="https://cdn.ravenjs.com/1.3.0/jquery,native/raven.min.js"></script>

<!-- -->
<script async src="thirdparty/remote/include.js"></script>

</body>
</html>
