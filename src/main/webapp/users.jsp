<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<div class="px-4 sm:px-6 lg:px-8 my-10">
    <div class="sm:flex sm:items-center">
        <div class="sm:flex-auto">
            <h1 class="text-base font-semibold leading-6 text-gray-900">Users</h1>
            <p class="mt-2 text-sm text-gray-700">A list of all the users in your account including their ID, name,
                email</p>
        </div>
        <div class="mt-4 sm:ml-16 sm:mt-0 sm:flex-none">
            <a href="/add-user"
               class="block rounded-md bg-indigo-600 px-3 py-2 text-center text-sm font-semibold text-white shadow-sm hover:bg-indigo-500">
                Add user
            </a>
        </div>
    </div>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <div class="mt-8 flow-root">
        <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
            <div class="inline-block min-w-full py-2 align-middle sm:px-6 lg:px-8">
                <div class="relative">
                    <%--                    if there are no users then display message--%>
                    <c:if test="${empty users}">
                        <div class="flex justify-center items-center">
                            <div class="flex flex-col items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-24 w-24 text-gray-400"
                                     viewBox="0 0 20 20" fill="currentColor">
                                    <path fill-rule="evenodd"
                                          d="M10 18a8 8 0 100-16 8 8 0 000 16zm0-2a6 6 0 100-12 6 6 0 000 12zm0-9a1 1 0 011 1v3a1 1 0 11-2 0V8a1 1 0 011-1z"
                                          clip-rule="evenodd"></path>
                                </svg>
                                <p class="text-gray-500 text-lg mt-4">No users found</p>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty users}">
                        <table class="min-w-full table-fixed divide-y divide-gray-300">
                            <thead>
                            <tr>
                                <th scope="col"
                                    class="py-3.5 pr-3 text-left text-sm font-semibold text-gray-900">ID
                                </th>
                                <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">Name
                                </th>
                                <th scope="col" class="px-3 py-3.5 text-left text-sm font-semibold text-gray-900">
                                    Email
                                </th>
                                <th scope="col" class="relative py-3.5 pl-3 pr-4 sm:pr-3">
                                    <span class="sr-only">Edit</span>
                                </th>
                            </tr>
                            </thead>
                            <tbody class="divide-y divide-gray-200 bg-white">
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td class="whitespace-nowrap py-4 pr-3 text-sm font-medium text-gray-900">${user.getId()}
                                    </td>
                                    <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">${user.getUsername()}</td>
                                    <td class="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                                            ${user.getEmail()}
                                    </td>
                                    <td class="whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-3 text-blue-500">
                                        <form action="/update-user" method="GET">
                                            <input type="hidden" name="id" value="${user.getId()}">
                                            <button type="submit">Update</button>
                                        </form>
                                    </td>
                                    <td class="whitespace-nowrap px-3 py-4 text-sm text-red-500">
                                        <form action="/delete-user" method="POST">
                                            <input type="hidden" name="id" value="${user.getId()}">
                                            <button type="submit">Delete</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
    <c:if test="${sessionScope.success != null}">
        <span style="color: forestgreen">${sessionScope.success}</span>
        <c:remove scope="session" var="success"/>
    </c:if>
    <c:if test="${sessionScope.error != null}">
        <span style="color: red">${sessionScope.error}</span>
        <c:remove scope="session" var="error"/>
    </c:if>
</div>
</body>
</html>

