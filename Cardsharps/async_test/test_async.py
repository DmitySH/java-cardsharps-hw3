def make_dict(path='files/java_out.txt'):
    d = dict()
    test_results = ''

    with open(path, 'r') as file:
        for line in file:
            if 'got' in line:
                line = line.split()
                d[line[0]] = d.get(line[0], {'score': 0, 'from cards': 0,
                                             'stole': 0})

                d[line[0]]['score'] += int(line[2])
                d[line[0]]['from cards'] += int(line[2])

            elif 'from' in line:
                line = line.split()
                d[line[5]] = d.get(line[5], {'score': 0, 'from cards': 0,
                                             'stole': 0})
                d[line[5]]['score'] -= int(line[2])
                d[line[5]]['stole'] -= int(line[2])

                d[line[0]] = d.get(line[0],
                                   {'score': 0, 'from cards': 0,
                                    'stole': 0})

                d[line[0]]['score'] += int(line[2])
                d[line[0]]['stole'] += int(line[2])

            if 'has' in line:
                words = line.split()
                d[words[0]] = d.get(words[0],
                                    {'score': 0, 'from cards': 0,
                                     'stole': 0})

                if int(words[2]) == int(d[words[0]]['score']):
                    test_results += line.replace('\n', '') + '  +++OK' + '\n'
                else:
                    test_results += line.replace('\n',
                                                 '') + '  -------ERROR' + '\n'

    return d, test_results


if __name__ == '__main__':
    res = make_dict('async_test/files/java_out.txt')
    print('\n'.join(map(lambda x: str(x).replace("'", '')[1:-1],
                        sorted(res[0].items(), key=lambda x: x[1]['score']))))
    print()
    print(res[1] + ("SUCCESS" if 'ERROR' not in res[1] else "ERRORS"))
    for x in res[1].split('\n'):
        if 'ERROR' in x:
            print(x)
            print(res[0][x.split()[0]])
