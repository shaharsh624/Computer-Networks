import socket
import threading
import time

host = "192.168.56.1"
port = 55555

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((host, port))
server.listen()

clients = []
nicknames = []


def broadcast(message):
    for client in clients:
        client.send(message)


def handle(client):
    while True:
        try:
            message = client.recv(1024)
            broadcast(message)
        except:
            index = clients.index(client)
            clients.remove(client)
            client.close()
            nickname = nicknames[index]
            broadcast(f"{nickname} left the chat".encode("ascii"))
            nicknames.remove(nickname)
            break


def recieve():
    while True:
        client, address = server.accept()
        print(f"Connected with {str(address)}")
        client.send("FIRST".encode("ascii"))

        nickname = client.recv(1024).decode("ascii")

        nicknames.append(nickname)
        clients.append(client)

        print(f"Nickname of the client is {nickname}")
        broadcast(f"{nickname} joined".encode("ascii"))
        client.send("Connected to server".encode("ascii"))

        thread = threading.Thread(target=handle, args=(client,))
        thread.start()


print("Server is Listening")
recieve()
