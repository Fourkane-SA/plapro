<div class="menu container">
	<div class="row">
		<div class="col">
			<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40"
				fill="currentColor" class="bi bi-house" viewBox="0 0 16 16"
				onclick="location.href = 'home'">
			  <path fill-rule="evenodd"
					d="M2 13.5V7h1v6.5a.5.5 0 0 0 .5.5h9a.5.5 0 0 0 .5-.5V7h1v6.5a1.5 1.5 0 0 1-1.5 1.5h-9A1.5 1.5 0 0 1 2 13.5zm11-11V6l-2-2V2.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5z" />
			  <path fill-rule="evenodd"
					d="M7.293 1.5a1 1 0 0 1 1.414 0l6.647 6.646a.5.5 0 0 1-.708.708L8 2.207 1.354 8.854a.5.5 0 1 1-.708-.708L7.293 1.5z" />
			</svg>
		</div>
		<div class="col">
			<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40"
				fill="currentColor" class="bi bi-chat" viewBox="0 0 16 16"
				onclick="location.href = 'messages'">
			  <path
					d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z" />
			</svg>
		</div>
		<div class="col">
			<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40"
				fill="currentColor" class="bi bi-search" viewBox="0 0 16 16"
				onclick="location.href = 'search'">
				  <path
					d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
			</svg>
		</div>
		<div class="col">
			<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40"
				fill="currentColor" class="bi bi-bell-fill" viewBox="0 0 16 16"
				onclick="location.href = 'notif'">
				<c:if test="${ not empty notifications }">
					<path
						d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zm.995-14.901a1 1 0 1 0-1.99 0A5.002 5.002 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901z" />
				</c:if>
				<c:if test="${ empty notifications }">
					<path
						d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
				</c:if>
			</svg>
		</div>
		<div class="col">
			<svg xmlns="http://www.w3.org/2000/svg" width="40" height="40"
				fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16"
				onclick="location.href = 'profile'">
			  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
			  <path fill-rule="evenodd"
					d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
			</svg>
		</div>
	</div>
</div>