//const socket = new SockJS('/ws'); // Подключение к WebSocket endpoint
//const stompClient = Stomp.over(socket);
//
//stompClient.connect({}, function () {
//    stompClient.subscribe('/topic/orders', function (order) {
//        const orderData = JSON.parse(order.body);
//        const newOrderRow = `
//            <tr>
//                <td>${orderData.id}</td>
//                <td>${orderData.orderName}</td>
//                <td>${orderData.orderDescription}</td>
//                <td>${orderData.status}</td>
//                <td></td>
//            </tr>`;
//        const tableBody = document.querySelector('tbody');
//        tableBody.insertAdjacentHTML('beforeend', newOrderRow);
//    });
//});
//
//function createOrder(firstName, lastName, orderName, orderDescription) {
//    $.ajax({
//        type: 'POST',
//        url: '/create-order',
//        data: {
//            firstName: firstName,
//            lastName: lastName,
//            orderName: orderName,
//            orderDescription: orderDescription
//        },
//        success: function (data) {
//            // Обработка успешного создания заказа, если необходимо
//        },
//        error: function (error) {
//            console.error('Ошибка при создании заказа:', error);
//        }
//    });
//}
//
//// Добавьте дополнительные функции обработки заказов, если необходимо
const socket = new SockJS('/ws'); // Подключение к WebSocket endpoint
const stompClient = Stomp.over(socket);

stompClient.connect({}, function () {
    stompClient.subscribe('/topic/orders', function (order) {
        const orderData = JSON.parse(order.body);
        const newRow = document.createElement('tr');
        newRow.innerHTML = `
            <td>${orderData.id}</td>
            <td>${orderData.orderName}</td>
            <td>${orderData.orderDescription}</td>
            <td>${orderData.status}</td>
            <td>
                <form action="/orders/${orderData.id}" method="post">
                    <input type="hidden" name="id" value="${orderData.id}">
                    <select name="status" value = " ">
                        <option value="ACCEPTED">Прийняти</option>
                        <option value="REJECTED">Відклонити</option>
                    </select>
                    <button type="submit">Оновити статус</button>
                </form>
            </td>
        `;

        const tableBody = document.querySelector('tbody');
        tableBody.appendChild(newRow);
    });
});
