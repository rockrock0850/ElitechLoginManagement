<%@ page pageEncoding="UTF-8" %>
<%@ include file='/WEB-INF/pages/jsp/layout/material/globle.jsp' %>

<script type="text/javascript" src="${contextPath}/pages/js/layout/material/menu.js"></script>
<div id='menu' class='menu'>
	<div class="demo clearfix">
		<div id="dl-menu" class="dl-menuwrapper">
			<button class="dl-trigger">Open Menu</button>
			<ul class="dl-menu">
				<li>
					<a href="#">人事資料管理</a>
					<ul class="dl-submenu">
						<li>
							<a href="#">人事管理</a>
							<ul class="dl-submenu">
								<li>
									<a href="#">組織管理</a>
								</li>
								<li>
									<a href="#">員工管理</a>
									<ul class="dl-submenu">
										<li>
											<a href="#">基本資料維護</a>
										</li>
										<li>
											<a href="#">調派</a>
										</li>
										<li>
											<a href="#">查詢</a>
										</li>
										<li>
											<a href="#">批次新增/修改作業</a>
										</li>
									</ul>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">屬性設定</a>
							<ul class="dl-submenu">
								<li>
									<a href="#">職稱資料維護</a>
								</li>
								<li>
									<a href="#">員工別資料維護</a>
								</li>
								<li>
									<a href="#">學歷資料維護</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">群組權限管理</a>
						</li>
					</ul>
				</li>
				<li>
					<a href="#">請假管理</a>
					<ul class="dl-submenu">
						<li>
							<a href="#">請假參數設定</a>
							<ul class="dl-submenu">
								<li>
									<a href="#">假別管理</a>
								</li>
								<li>
									<a href="#">員工假別可休天數管理</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
				<li>
					<a href="#">個人首頁</a>
					<ul class="dl-submenu">
						<li>
							<a href="#">申請業務</a>
							<ul class="dl-submenu">
								<li>
									<a href="#">請假申請單</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">簽核業務</a>
							<ul class="dl-submenu">
								<li>
									<a href="#">請假簽核</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="#">表單查詢</a>
							<ul class="dl-submenu">
								<li>
									<a href="#">個人表單追蹤</a>
								</li>
								<li>
									<a href="#">表單簽核紀錄</a>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>