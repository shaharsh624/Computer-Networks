import socket
import threading

nickname = input("Enter nickname: ")

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect(("192.168.56.1", 55555))

stop_thread = False


def receive():
    while True:
        global stop_thread
        if stop_thread:
            break
        try:
            message = client.recv(1024).decode("ascii")
            if message == "FIRST":
                client.send(nickname.encode("ascii"))
            else:
                print(message)
        except:
            print("An error occurred!")
            client.close()
            break


def write():
    while True:
        if stop_thread:
            break
        message = f'{nickname}: {input("")}'
        client.send(message.encode("ascii"))


receive_thread = threading.Thread(target=receive)
receive_thread.start()
write_thread = threading.Thread(target=write)
write_thread.start()
