#!/usr/bin/python3

def extract_topic(path):
    return path.split('/')[-2]

if __name__ == "__main__":
    with open('titles.txt', 'r') as fp:
        titles = fp.readlines()

    with open('paths.txt', 'r') as file:
        paths = file.readlines()

    print('# algorithms\n')
    print('## LeetCode\n')
    print('| Category | Problem |')
    print('| --------- | ------------------ |')

    topic = ''
    for i in range(len(paths)):
        display_topic = ' '
        tp = extract_topic(paths[i])
        if tp != topic:
            topic = tp
            display_topic = tp.capitalize()
        print('| {} | {} |'.format(display_topic, '[{}]({})\n'.format(titles[i].strip(), paths[i].strip())))
        #print('[{}]({})\n'.format(titles[i].strip(), paths[i].strip()))