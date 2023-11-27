import socket

server = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server.bind(('127.0.0.1', 9999))

message, address = server.recvfrom(1024).decode('utf-8')
print(message)

server.sendto("Hello Client!".encode('utf-8'), address)