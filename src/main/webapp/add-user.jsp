<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="h-full bg-white">
<head>
    <title>JSP - Hello eWorld</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="h-full">
    <div class="w-full h-full flex flex-col gap-4 justify-center items-center">
        <h1 class="text-xl">Create User</h1>
        <form class="space-y-6 w-[30%]" id='form' action="/add-user" method="POST">
            <div>
                <label for="username" class="block text-sm font-medium leading-6 text-gray-900">Name</label>
                <div class="mt-2">
                    <input id="username" name="username" type="text" autocomplete="username" required class="block w-full rounded-md border-0 py-1.5 px-1 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                </div>
            </div>

            <div>
                <div class="flex items-center justify-between">
                    <label for="email" class="block text-sm font-medium leading-6 text-gray-900">Email</label>
                </div>
                <div class="mt-2">
                    <input id="email" name="email" type="email" autocomplete="current-email" required class="block w-full rounded-md border-0 py-1.5 px-1 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                </div>
            </div>

            <div>
                <button type="submit" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Save</button>
            </div>
        </form>
        <c:if test="${sessionScope.success != null}">
            <span style="color: forestgreen">${sessionScope.success}</span>
            <c:remove scope="session" var="success"/>
        </c:if>
        <c:if test="${sessionScope.error != null}">
            <span style="color: red">${sessionScope.error}</span>
            <c:remove scope="session" var="error"/>
        </c:if>
        <a href="/all-users" class="text-indigo-600 underline">See all users</a>
    </div>
</body>
</html>